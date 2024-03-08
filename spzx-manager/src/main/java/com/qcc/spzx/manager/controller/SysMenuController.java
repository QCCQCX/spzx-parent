package com.qcc.spzx.manager.controller;

import com.qcc.spzx.manager.service.SysMenuService;
import com.qcc.spzx.model.entity.system.SysMenu;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SysMenuController
 * @Description: 菜单表现层
 * @Date 2024/1/13 10:07
 * @Author quchenxi
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/system/sysMenu")
@Tag(name = "菜单接口")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * @title findNodes
     * @description 菜单列表
     * @author quchenxi
     * @date 2024/1/13 10:23
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "菜单列表")
    @GetMapping("/findNodes")
    public Result findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title save
     * @description 菜单添加
     * @author quchenxi
     * @date 2024/1/13 11:51
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "菜单添加")
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title update
     * @description 菜单修改
     * @author quchenxi
     * @date 2024/1/13 11:57
     * @param sysMenu
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "菜单修改")
    @PutMapping("/update")
    public Result update(@RequestBody SysMenu sysMenu) {
        sysMenuService.update(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title removeById
     * @description 菜单删除
     * @author quchenxi
     * @date 2024/1/13 12:04
     */
    @Operation(summary = "菜单删除")
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable("id") Long id) {
        sysMenuService.removeById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
