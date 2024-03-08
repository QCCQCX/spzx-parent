package com.qcc.spzx.user.controller;

import com.qcc.spzx.model.dto.h5.UserLoginDto;
import com.qcc.spzx.model.dto.h5.UserRegisterDto;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.model.vo.h5.UserInfoVo;
import com.qcc.spzx.user.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: UserInfoController
 * @Description: 用户信息接口
 * @Date 2024/2/1 18:48
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "用户信息接口")
@RestController
@RequestMapping("/api/user/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * @title register
     * @description 用户注册
     * @author quchenxi
     * @date 2024/2/1 18:51
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        userInfoService.register(userRegisterDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title login
     * @description 用户登录
     * @author quchenxi
     * @date 2024/2/2 14:02
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        String token = userInfoService.login(userLoginDto);
        return Result.build(token, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title getCurrentUserInfo
     * @description 获取当前登录用户信息
     * @author quchenxi
     * @date 2024/2/2 14:22
     */
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/auth/getCurrentUserInfo")
    public Result getCurrentUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        UserInfoVo userInfoVo = userInfoService.getCurrentUserInfo(token);
        return Result.build(userInfoVo, ResultCodeEnum.SUCCESS);
    }
}
