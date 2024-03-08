package com.qcc.spzx.manager.service.impl;

import com.qcc.spzx.manager.mapper.SysMenuMapper;
import com.qcc.spzx.manager.mapper.SysRoleMenuMapper;
import com.qcc.spzx.manager.service.SysMenuService;
import com.qcc.spzx.manager.service.SysRoleMenuService;
import com.qcc.spzx.model.dto.system.AssignMenuDto;
import com.qcc.spzx.model.entity.system.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysRoleMenuServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/13 13:11
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * @param roleId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @title findSysRoleMenuByRoleId
     * @description 查询所有菜单和角色分配的菜单id列表
     * @author quchenxi
     * @date 2024/1/13 13:19
     */
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        // 查询所有菜单列表
        List<SysMenu> sysMenuList = sysMenuService.findNodes();

        // 查询角色分配的菜单id列表
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);

        HashMap<String, Object> map = new HashMap<>();
        map.put("sysMenuList", sysMenuList); // 将所有菜单列表放到map中
        map.put("roleMenuIds", roleMenuIds); // 将角色分配的菜单id列表放到map中
        return map;
    }

    /**
     * @title doAssign
     * @description 角色分配菜单
     * @author quchenxi
     * @date 2024/1/13 14:03
     * @param assignMenuDto
     * @return void
     */
    @Override
    public void doAssign(AssignMenuDto assignMenuDto) {
        // 删除角色分配的所有菜单
        sysRoleMenuMapper.deleteByRoleId(assignMenuDto.getRoleId());

        // 为角色分配菜单
        List<Map<String, Number>> menuInfo = assignMenuDto.getMenuIdList();
        if (menuInfo != null && menuInfo.size() > 0) { // 判断是否为角色分配了菜单
            sysRoleMenuMapper.doAssign(assignMenuDto);
        }
    }
}
