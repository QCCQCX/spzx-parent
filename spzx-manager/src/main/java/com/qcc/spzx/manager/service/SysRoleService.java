package com.qcc.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.model.dto.system.SysRoleDto;
import com.qcc.spzx.model.entity.system.SysRole;

import java.util.Map;

/**
 * @ClassName: SysRoleService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/11 20:05
 * @Author quchenxi
 * @Version 1.0
 */
public interface SysRoleService {

    /**
     * @title findByPage
     * @description 角色列表
     * @author quchenxi
     * @date 2024/1/11 21:15
     * @param sysRoleDto
     * @param current
     * @param limit
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.system.SysRole>
     */
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit);

    /**
     * @title saveSysRole
     * @description 角色添加
     * @author quchenxi
     * @date 2024/1/11 21:15
     * @param sysRole
     * @return void
     */
    void saveSysRole(SysRole sysRole);

    /**
     * @title updateSysRole
     * @description 角色修改
     * @author quchenxi
     * @date 2024/1/12 11:53
     * @param sysRole
     * @return void
     */
    void updateSysRole(SysRole sysRole);

    /**
     * @title deleteById
     * @description 角色逻辑删除
     * @author quchenxi
     * @date 2024/1/12 13:22
     * @param roleId
     * @return void
     */
    void deleteById(Long roleId);

    /**
     * @param
     * @param userId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @title findAll
     * @description 查询所有角色
     * @author quchenxi
     * @date 2024/1/12 23:06
     */
    Map<String, Object> findAll(Long userId);
}
