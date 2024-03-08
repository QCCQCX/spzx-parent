package com.qcc.spzx.manager.controller;

import com.qcc.spzx.manager.service.SysRoleMenuService;
import com.qcc.spzx.model.dto.system.AssignMenuDto;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName: SysRoleMenuController
 * @Description: 角色菜单表现层
 * @Date 2024/1/13 13:10
 * @Author quchenxi
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/system/sysRoleMenu")
@Tag(name = "角色菜单接口")
public class SysRoleMenuController {
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * @title findSysRoleMenuByRoleId
     * @description 查询所有菜单和角色分配的菜单id列表
     * @author quchenxi
     * @date 2024/1/13 13:18
     * @param roleId
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "查询所有菜单和角色分配的菜单id列表")
    @GetMapping("/findSysRoleMenuByRoleId/{roleId}")
    public Result findSysRoleMenuByRoleId(@PathVariable("roleId") Long roleId) {
        Map<String, Object> map = sysRoleMenuService.findSysRoleMenuByRoleId(roleId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title doAssign
     * @description 角色分配菜单
     * @author quchenxi
     * @date 2024/1/13 13:41
     */
    @Operation(summary = "角色分配菜单")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssignMenuDto assignMenuDto) {
        sysRoleMenuService.doAssign(assignMenuDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
