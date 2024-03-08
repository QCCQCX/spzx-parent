package com.qcc.spzx.user.service;

import com.qcc.spzx.model.dto.h5.UserLoginDto;
import com.qcc.spzx.model.dto.h5.UserRegisterDto;
import com.qcc.spzx.model.vo.h5.UserInfoVo;

/**
 * @ClassName: UserInfoService
 * @Description: 此处输入类描述信息
 * @Date 2024/2/1 18:47
 * @Author quchenxi
 * @Version 1.0
 */
public interface UserInfoService {
    /**
     * @title register
     * @description 用户注册
     * @author quchenxi
     * @date 2024/2/1 18:53
     * @param userRegisterDto
     * @return void
     */
    void register(UserRegisterDto userRegisterDto);

    /**
     * @title login
     * @description 用户登录
     * @author quchenxi
     * @date 2024/2/2 14:03
     * @param userLoginDto
     * @return java.lang.String
     */
    String login(UserLoginDto userLoginDto);

    /**
     * @title getCurrentUserInfo
     * @description 获取当前登录用户信息
     * @author quchenxi
     * @date 2024/2/2 14:25
     * @param token
     * @return com.qcc.spzx.model.vo.h5.UserInfoVo
     */
    UserInfoVo getCurrentUserInfo(String token);
}
