package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: SysMenuMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/13 10:09
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface SysMenuMapper {
    /**
     * @title findAll
     * @description 菜单列表
     * @author quchenxi
     * @date 2024/1/13 10:28
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.system.SysMenu>
     */
    List<SysMenu> findAll();

    /**
     * @title save
     * @description 菜单添加
     * @author quchenxi
     * @date 2024/1/13 11:53
     * @param sysMenu
     * @return void
     */
    void save(SysMenu sysMenu);

    /**
     * @title update
     * @description 菜单修改
     * @author quchenxi
     * @date 2024/1/13 11:58
     * @param sysMenu
     * @return void
     */
    void update(SysMenu sysMenu);

    /**
     * @title selectCountById
     * @description 根据节点id查询子节点
     * @author quchenxi
     * @date 2024/1/13 12:36
     * @param id
     * @return int
     */
    int selectCountById(Long id);

    /**
     * @title delete
     * @description 菜单删除
     * @author quchenxi
     * @date 2024/1/13 12:37
     * @param id
     * @return void
     */
    void delete(Long id);

    /**
     * @title findMenusByUserId
     * @description 查询用户菜单
     * @author quchenxi
     * @date 2024/1/13 15:23
     * @param userId
     * @return java.util.List<com.qcc.spzx.model.entity.system.SysMenu>
     */
    List<SysMenu> findMenusByUserId(Long userId);

    /**
     * @title selectMenu
     * @description 根据id获取节点
     * @author quchenxi
     * @date 2024/1/13 17:04
     * @param id
     * @return com.qcc.spzx.model.entity.system.SysMenu
     */
    SysMenu selectMenu(Long id);
}
