package com.qcc.spzx.manager.controller;

import com.qcc.spzx.manager.service.SysMenuService;
import com.qcc.spzx.manager.service.SysUserService;
import com.qcc.spzx.manager.service.ValidateCodeService;
import com.qcc.spzx.model.dto.system.LoginDto;
import com.qcc.spzx.model.entity.system.SysUser;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.model.vo.system.LoginVo;
import com.qcc.spzx.model.vo.system.SysMenuVo;
import com.qcc.spzx.model.vo.system.ValidateCodeVo;
import com.qcc.spzx.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: IndexController
 * @Description: 首页表现层
 * @Date 2024/1/10 18:45
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "首页接口") // swagger上的接口中文名
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * @title login
     * @description 用户登录
     * @author quchenxi
     * @date 2024/1/10 21:46
     * @param loginDto
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "登录方法") // swagger上的方法中文名
    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title generateValidateCode
     * @description 生成图片验证码
     * @author quchenxi
     * @date 2024/1/10 21:46
     * @param
     * @return com.qcc.spzx.model.vo.common.Result<com.qcc.spzx.model.vo.system.ValidateCodeVo>
     */
    @Operation(summary = "生成验证码")
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title getUserInfo
     * @description 获取当前登录用户信息
     * @author quchenxi
     * @date 2024/1/10 22:58
     * @param token
     * @return com.qcc.spzx.model.vo.common.Result
     */
//    @Operation(summary = "根据token获取当前登录用户信息")
//    @GetMapping(value = "/getUserInfo")
//    public Result getUserInfo(@RequestHeader(name = "token") String token) {
//        // 根据token查询redis获取用户信息
//        SysUser sysUser = sysUserService.getUserInfo(token);
//        // 用户信息返回
//        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
//    }

    /**
     * @title getUserInfo
     * @description 获取当前登录用户信息（使用ThreadLocal获取信息，优化版）
     * @author quchenxi
     * @date 2024/1/11 15:27
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping(value = "/getUserInfo")
    public Result getUserInfo() {
        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);
    }

    /**
     * @title logout
     * @description 退出登录
     * @author quchenxi
     * @date 2024/1/10 23:19
     * @param token
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "退出登录")
    @GetMapping("/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title findMenusByUserId
     * @description 查询用户菜单
     * @author quchenxi
     * @date 2024/1/13 14:49
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "查询用户菜单")
    @GetMapping("/findMenusByUserId")
    public Result findMenusByUserId() {
        List<SysMenuVo> list = sysMenuService.findMenusByUserId();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
