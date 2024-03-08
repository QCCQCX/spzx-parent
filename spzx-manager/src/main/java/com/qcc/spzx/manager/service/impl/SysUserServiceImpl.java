package com.qcc.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qcc.spzx.common.exception.QccException;
import com.qcc.spzx.manager.mapper.SysRoleUserMapper;
import com.qcc.spzx.manager.mapper.SysUserMapper;
import com.qcc.spzx.manager.service.SysMenuService;
import com.qcc.spzx.manager.service.SysUserService;
import com.qcc.spzx.model.dto.system.AssignRoleDto;
import com.qcc.spzx.model.dto.system.LoginDto;
import com.qcc.spzx.model.dto.system.SysUserDto;
import com.qcc.spzx.model.entity.system.SysMenu;
import com.qcc.spzx.model.entity.system.SysUser;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.model.vo.system.LoginVo;
import com.qcc.spzx.model.vo.system.SysMenuVo;
import com.qcc.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SysUserServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/10 19:03
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    /**
     * @title login
     * @description 用户登录
     * @author quchenxi
     * @date 2024/1/10 19:23
     * @param loginDto
     * @return com.qcc.spzx.model.vo.system.LoginVo
     */
    @Override
    public LoginVo login(LoginDto loginDto) {

        // 获取输入的验证码和存储到redis的key（loginDto获取到）
        String captcha = loginDto.getCaptcha(); // 用户输入的验证码
        String codeKey = loginDto.getCodeKey(); // 生成验证码的时候UUID生成的（验证码的key的后半段）

        // 根据获取的验证码的key，查询redis中验证码的值
        String redisCode = redisTemplate.opsForValue().get("user:validate" + codeKey);

        // 判断验证码是否为空和验证码是否一致
        // 若为空或不一致，校验失败
        if (StrUtil.isEmpty(redisCode) || !StrUtil.equalsAnyIgnoreCase(redisCode, captcha)) {
            throw new QccException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        // 若一致，删除redis中的该验证码
        redisTemplate.delete("user:validate" + codeKey);

        // 获取提交的用户名
        String userName = loginDto.getUserName();

        // 根据提交的用户名查询用户信息
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(userName);

        // 用户不存在，返回错误信息
        if (sysUser == null) {
            throw new QccException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 用户存在，开始进行密码验证
        // 获取输入的密码，md5加密（因为数据库中存放的密码为加密形式）
        String input_password = loginDto.getPassword();
        input_password = DigestUtils.md5DigestAsHex(input_password.getBytes());

        // 密码一致，登陆成功，不一致，登录失败
        if (!sysUser.getPassword().equals(input_password)) {
            throw new QccException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 登陆成功，生成用户唯一标识token（UUID）
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        // 登陆成功用户信息放到redis里面
        redisTemplate.opsForValue()
                .set("user:login" + token, // 设置存储的key
                        JSON.toJSONString(sysUser), // 将用户信息转换成JSON字符串
                        7, // 有效期
                        TimeUnit.DAYS); // 有效期单位

        // 返回loginvo对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

    /**
     * @title getUserInfo
     * @description 获取当前登录用户信息
     * @author quchenxi
     * @date 2024/1/11 15:40
     * @param token
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Override
    public SysUser getUserInfo(String token) {

        // 根据token到redis中查询用户信息，返回JSON格式字符串
        String userJson = redisTemplate.opsForValue().get("user:login" + token);

        // 将JSON格式字符串转换为SysUser对象，并返回
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    /**
     * @param
     * @return void
     * @title logout
     * @description 退出登录
     * @author quchenxi
     * @date 2024/1/10 23:15
     */
    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login" + token);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param sysUserDto
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.system.SysUser>
     * @title findByPage
     * @description 用户条件分页查询
     * @author quchenxi
     * @date 2024/1/12 13:58
     */
    @Override
    public PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> list = sysUserMapper.findByPage(sysUserDto);
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * @title saveSysUser
     * @description 用户添加
     * @author quchenxi
     * @date 2024/1/12 14:40
     * @param sysUser
     * @return void
     */
    @Override
    public void saveSysUser(SysUser sysUser) {
        // 判断用户名是否存在
        String userName = sysUser.getUserName();
        SysUser dbSysUser = sysUserMapper.selectUserInfoByUserName(userName);
        if (dbSysUser != null) {
            throw new QccException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        // 在存入数据库前将输入密码进行md5加密
        String password = DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes());
        sysUser.setPassword(password);

        // 将status设置为1（可用）
        sysUser.setStatus(1);

        sysUserMapper.save(sysUser);
    }

    /**
     * @title updateSysUser
     * @description 用户修改
     * @author quchenxi
     * @date 2024/1/12 15:19
     * @param sysUser
     * @return void
     */
    @Override
    public void updateSysUser(SysUser sysUser) {
        // 判断用户名是否存在
        String userName = sysUser.getUserName();
        SysUser dbSysUser = sysUserMapper.selectUserInfoByUserName(userName);
        if (dbSysUser != null) {
            throw new QccException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        sysUserMapper.update(sysUser);
    }

    /**
     * @title deleteById
     * @description 用户逻辑删除
     * @author quchenxi
     * @date 2024/1/12 15:31
     * @param userId
     * @return void
     */
    @Override
    public void deleteById(Long userId) {
        sysUserMapper.delete(userId);
    }

    /**
     * @title doAssign
     * @description 用户分配角色
     * @author quchenxi
     * @date 2024/1/12 23:20
     * @param assignRoleDto
     * @return void
     */
    @Override
    public void doAssign(AssignRoleDto assignRoleDto) {
        // 根据userId删除用户之前分配角色数据
        sysRoleUserMapper.deleteByUserId(assignRoleDto.getUserId());

        // 重新分配该用户的新角色（多次调用底层的插入操作，每次插入都新增了一条用户角色分配数据）
        List<Long> roleIdList = assignRoleDto.getRoleIdList();
        for (Long roleId : roleIdList) {
            sysRoleUserMapper.doAssign(assignRoleDto.getUserId(), roleId);
        }
    }
}
