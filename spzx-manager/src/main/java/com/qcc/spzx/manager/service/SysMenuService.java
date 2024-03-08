package com.qcc.spzx.manager.service;

import com.qcc.spzx.model.entity.system.SysMenu;
import com.qcc.spzx.model.vo.system.SysMenuVo;

import java.util.List;

/**
 * @ClassName: SysMenuService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/13 10:08
 * @Author quchenxi
 * @Version 1.0
 */
public interface SysMenuService {
    /**
     * @title findNodes
     * @description 菜单列表
     * @author quchenxi
     * @date 2024/1/13 10:24
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.system.SysMenu>
     */
    List<SysMenu> findNodes();

    /**
     * @title save
     * @description 菜单添加
     * @author quchenxi
     * @date 2024/1/13 11:52
     * @param sysMenu
     * @return void
     */
    void save(SysMenu sysMenu);

    /**
     * @title update
     * @description 菜单修改
     * @author quchenxi
     * @date 2024/1/13 11:57
     * @param sysMenu
     * @return void
     */
    void update(SysMenu sysMenu);

    /**
     * @title removeById
     * @description 菜单删除
     * @author quchenxi
     * @date 2024/1/13 12:28
     * @param id
     * @return void
     */
    void removeById(Long id);

    /**
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.system.SysMenu>
     * @title findMenusByUserId
     * @description 查询用户菜单
     * @author quchenxi
     * @date 2024/1/13 14:51
     */
    List<SysMenuVo> findMenusByUserId();
}
