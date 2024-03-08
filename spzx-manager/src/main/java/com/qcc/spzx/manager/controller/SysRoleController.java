package com.qcc.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.common.log.annotation.Log;
import com.qcc.spzx.common.log.enums.OperatorType;
import com.qcc.spzx.manager.service.SysRoleService;
import com.qcc.spzx.model.dto.system.SysRoleDto;
import com.qcc.spzx.model.entity.system.SysRole;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static cn.hutool.core.util.ReUtil.findAll;

/**
 * @ClassName: SysRoleController
 * @Description: 角色表现层
 * @Date 2024/1/11 20:03
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "角色接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * @title findByPage
     * @description 角色列表
     * @author quchenxi
     * @date 2024/1/11 20:28
     * @param current
     * @param limit
     * @param sysRoleDto
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "角色列表")
    @Log(title = "角色管理:列表", businessType = 0, operatorType = OperatorType.OTHER)
    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit,
                             @RequestBody SysRoleDto sysRoleDto) {
        // pageHelper插件实现分页
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto, current, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title saveSysRole
     * @description 角色添加
     * @author quchenxi
     * @date 2024/1/11 21:14
     * @param sysRole
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "角色添加")
    @PostMapping("/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.saveSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title updateSysRole
     * @description 角色修改
     * @author quchenxi
     * @date 2024/1/12 11:44
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "角色修改")
    @PutMapping("/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title deleteById
     * @description 角色逻辑删除
     * @author quchenxi
     * @date 2024/1/12 13:20
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "角色逻辑删除")
    @DeleteMapping("/deleteById/{roleId}")
    public Result deleteById(@PathVariable Long roleId) {
        sysRoleService.deleteById(roleId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title findAllRoles
     * @description 查询所有角色，并查询用户所有分配角色id
     * @author quchenxi
     * @date 2024/1/12 23:04
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "查询所有角色和用户所有角色id")
    @GetMapping("/findAllRoles/{userId}")
    public Result findAllRoles(@PathVariable("userId") Long userId) {
        // map中存储两对数据，一对为所有角色列表，一对为用户所有分配角色id列表
        Map<String, Object> map = sysRoleService.findAll(userId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }
}
