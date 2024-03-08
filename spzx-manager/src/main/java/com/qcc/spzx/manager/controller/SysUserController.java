package com.qcc.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.manager.service.SysUserService;
import com.qcc.spzx.model.dto.system.AssignRoleDto;
import com.qcc.spzx.model.dto.system.SysUserDto;
import com.qcc.spzx.model.entity.system.SysRole;
import com.qcc.spzx.model.entity.system.SysUser;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SysUserController
 * @Description: 用户表现层
 * @Date 2024/1/12 13:38
 * @Author quchenxi
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/system/sysUser")
@Tag(name = "用户接口")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * @param pageNum
     * @param pageSize
     * @return com.qcc.spzx.model.vo.common.Result
     * @title findByPage
     * @description 用户条件分页查询
     * @author quchenxi
     * @date 2024/1/12 13:49
     */
    @Operation(summary = "用户条件分页查询")
    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             @RequestBody SysUserDto sysUserDto) {
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(pageNum, pageSize, sysUserDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title saveSysUser
     * @description 用户添加
     * @author quchenxi
     * @date 2024/1/12 14:40
     * @param sysUser
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "用户添加")
    @PostMapping("/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.saveSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title updateSysUser
     * @description 用户修改
     * @author quchenxi
     * @date 2024/1/12 15:19
     * @param sysUser
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "用户修改")
    @PutMapping("/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        sysUserService.updateSysUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title deleteById
     * @description 用户逻辑删除
     * @author quchenxi
     * @date 2024/1/12 15:30
     * @param userId
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "用户逻辑删除")
    @DeleteMapping("/deleteById/{userId}")
    public Result deleteById(@PathVariable("userId") Long userId) {
        sysUserService.deleteById(userId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title doAssign
     * @description 用户分配角色
     * @author quchenxi
     * @date 2024/1/12 23:20
     * @param assignRoleDto
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssignRoleDto assignRoleDto) {
        sysUserService.doAssign(assignRoleDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
