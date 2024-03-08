package com.qcc.spzx.manager.utils;

import com.qcc.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MenuHelper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/13 10:35
 * @Author quchenxi
 * @Version 1.0
 */
public class MenuHelper {

    /**
     * @title buildTree
     * @description 将list封装为树形菜单数据
     * @author quchenxi
     * @date 2024/1/13 10:37
     * @param list
     * @return java.util.List<com.qcc.spzx.model.entity.system.SysMenu>
     */
    public static List<SysMenu> buildTree(List<SysMenu> list) {
        // 创建list，用于盛放树形菜单数据（所有根节点）
        List<SysMenu> trees = new ArrayList<>();

        // 遍历所有菜单节点，寻找所有根节点
        for (SysMenu sysMenu : list) {
            if (sysMenu.getParentId().longValue() == 0) {
                // 将该根节点放入trees中
                trees.add(findChildren(sysMenu, list));
            }
        }
        // 返回树结构
        return trees;
    }

    /**
     * @title findChildren
     * @description 递归查找菜单节点的所有子节点
     * @author quchenxi
     * @date 2024/1/13 11:11
     * @param sysMenu
     * @param list
     * @return com.qcc.spzx.model.entity.system.SysMenu
     */
    public static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> list) {
        // 创建当前菜单节点的子节点list
        sysMenu.setChildren(new ArrayList<>());
        // 遍历所有菜单节点，寻找当前菜单节点的子节点
        for (SysMenu it : list) {
            // 若当前菜单节点的id等于当前遍历菜单节点的父节点id
            if (sysMenu.getId().longValue() == it.getParentId().longValue()) {
                // 递归寻找到当前遍历菜单节点的所有子节点，并添加到当前菜单节点的子节点list中
                sysMenu.getChildren().add(findChildren(it, list));
            }
        }
        // 返回当前菜单节点
        return sysMenu;
    }
}
