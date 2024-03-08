package com.qcc.spzx.manager.service;

import com.qcc.spzx.model.dto.system.AssignMenuDto;

import java.util.Map;

/**
 * @ClassName: SysRoleMenuService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/13 13:11
 * @Author quchenxi
 * @Version 1.0
 */
public interface SysRoleMenuService {
    /**
     * @title findSysRoleMenuByRoleId
     * @description 查询所有菜单和角色分配的菜单id列表
     * @author quchenxi
     * @date 2024/1/13 13:18
     * @param roleId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    /**
     * @title doAssign
     * @description 角色分配菜单
     * @author quchenxi
     * @date 2024/1/13 14:02
     * @param assignMenuDto
     * @return void
     */
    void doAssign(AssignMenuDto assignMenuDto);
}
