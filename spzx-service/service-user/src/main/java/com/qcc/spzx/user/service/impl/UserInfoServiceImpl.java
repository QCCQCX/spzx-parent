package com.qcc.spzx.user.service.impl;

import com.alibaba.fastjson2.JSON;
import com.qcc.spzx.common.exception.QccException;
import com.qcc.spzx.model.dto.h5.UserLoginDto;
import com.qcc.spzx.model.dto.h5.UserRegisterDto;
import com.qcc.spzx.model.entity.user.UserInfo;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.model.vo.h5.UserInfoVo;
import com.qcc.spzx.user.mapper.UserInfoMapper;
import com.qcc.spzx.user.service.UserInfoService;
import com.qcc.spzx.utils.AuthContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: UserInfoServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/2/1 18:47
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * @title register
     * @description 用户注册
     * @author quchenxi
     * @date 2024/2/1 18:54
     * @param userRegisterDto
     * @return void
     */
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        // 获取数据
        String username = userRegisterDto.getUsername(); // 即手机号或邮箱
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        // 验证码校验
        String redisCode = redisTemplate.opsForValue().get(username);
        if (!redisCode.equals(code)) {
            throw new QccException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        // 用户名重复性校验
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        if (userInfo != null) {
            throw new QccException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        // 封装数据
        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");

        // 添加到数据库
        userInfoMapper.save(userInfo);

        // 删除redis中的验证码
        redisTemplate.delete(username);
    }

    /**
     * @title login
     * @description 用户登录
     * @author quchenxi
     * @date 2024/2/2 14:03
     * @param userLoginDto
     * @return java.lang.String
     */
    @Override
    public String login(UserLoginDto userLoginDto) {
        // 获取数据
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        // 查询数据库，得到用户信息
        UserInfo userInfo = userInfoMapper.selectByUsername(username);

        // 密码校验
        String db_password = userInfo.getPassword();
        String md5_password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!md5_password.equals(db_password)) {
            throw new QccException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 判断用户状态（是否禁用）
        if (userInfo.getStatus() == 0) {
            throw new QccException(ResultCodeEnum.ACCOUNT_STOP);
        }

        // 生成token
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        // 用户信息放到redis中
        redisTemplate.opsForValue()
                .set("user:spzx:" + token,
                JSON.toJSONString(userInfo),
                30, TimeUnit.DAYS);

        // 返回token
        return token;
    }

    /**
     * @title getCurrentUserInfo
     * @description 获取当前登录用户信息
     * @author quchenxi
     * @date 2024/2/2 14:26
     * @param token
     * @return com.qcc.spzx.model.vo.h5.UserInfoVo
     */
    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
//        // 从redis中获取用户信息
//        String userJson = redisTemplate.opsForValue().get("user:spzx:" + token);
//        // 为空，抛异常
//        if (!StringUtils.hasText(userJson)) {
//            throw new QccException(ResultCodeEnum.LOGIN_ERROR);
//        }
//        // 不为空，将JSON格式字符串转换为UserInfo对象
//        UserInfo userInfo = JSON.parseObject(userJson, UserInfo.class);

        // 从ThreadLocal获取用户信息
        UserInfo userInfo = AuthContextUtil.getUserInfo();

        // 将UserInfo对象封装为UserInfoVo对象，并返回
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo;
    }
}
