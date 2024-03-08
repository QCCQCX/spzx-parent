package com.qcc.spzx.manager.service.impl;

import com.qcc.spzx.common.exception.QccException;
import com.qcc.spzx.manager.mapper.SysMenuMapper;
import com.qcc.spzx.manager.mapper.SysRoleMenuMapper;
import com.qcc.spzx.manager.service.SysMenuService;
import com.qcc.spzx.manager.utils.MenuHelper;
import com.qcc.spzx.model.entity.system.SysMenu;
import com.qcc.spzx.model.entity.system.SysUser;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.model.vo.system.SysMenuVo;
import com.qcc.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: SysMenuServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/13 10:08
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * @title findNodes
     * @description 菜单列表
     * @author quchenxi
     * @date 2024/1/13 10:24
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.system.SysMenu>
     */
    @Override
    public List<SysMenu> findNodes() {

        // 查询所有菜单，返回list集合
        List<SysMenu> list = sysMenuMapper.findAll();

        // 调用自定义工具类的方法，将list集合封装要求数据格式，并返回
        List<SysMenu> treeList = MenuHelper.buildTree(list);
        return treeList;
    }

    /**
     * @title save
     * @description 菜单添加
     * @author quchenxi
     * @date 2024/1/13 11:53
     * @param sysMenu
     * @return void
     */
    @Override
    public void save(SysMenu sysMenu) {
        sysMenuMapper.save(sysMenu); // 添加菜单节点

        updateSysRoleMenu(sysMenu); // 更新菜单节点的父节点状态
    }

    public void updateSysRoleMenu(SysMenu sysMenu) {
        // 获取当前菜单节点的父节点
        SysMenu parentMenu = sysMenuMapper.selectMenu(sysMenu.getParentId());

        // 把父节点isHalf状态设置为1，半开状态
        if (parentMenu != null) {
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId());
            updateSysRoleMenu(parentMenu); // 递归修改所有节点状态
        }
    }

    /**
     * @title update
     * @description 菜单修改
     * @author quchenxi
     * @date 2024/1/13 11:58
     * @param sysMenu
     * @return void
     */
    @Override
    public void update(SysMenu sysMenu) {
        sysMenuMapper.update(sysMenu);
    }

    /**
     * @title removeById
     * @description 菜单删除
     * @author quchenxi
     * @date 2024/1/13 12:29
     * @param id
     * @return void
     */
    @Override
    public void removeById(Long id) {
        // 根据节点id查询子节点数量
        int count = sysMenuMapper.selectCountById(id);

        // 若要删除节点包含子节点，则不能删除
        if (count > 0) {
            throw new QccException(ResultCodeEnum.NODE_ERROR);
        }

        sysMenuMapper.delete(id);
    }

    /**
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.system.SysMenu>
     * @title findMenusByUserId
     * @description 查询用户菜单
     * @author quchenxi
     * @date 2024/1/13 14:51
     */
    @Override
    public List<SysMenuVo> findMenusByUserId() {
        // 获取当前用户id
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();

        // 根据用户id查询菜单
        List<SysMenu> userMenuList = sysMenuMapper.findMenusByUserId(userId);

        // 封装要求数据格式，并返回
        // 将菜单列表转换为树形结构
        List<SysMenu> sysMenusList = MenuHelper.buildTree(userMenuList);
        // 将树形结构的菜单列表转换为List<SysMenuVo>
        List<SysMenuVo> sysMenuVoList = buildMenus(sysMenusList);

        return sysMenuVoList;
    }

    /**
     * @title buildMenus
     * @description 用于将List<SysMenu>转换为List<SysMenuVo>
     * @author quchenxi
     * @date 2024/1/13 15:14
     * @param menus
     * @return java.util.List<com.qcc.spzx.model.vo.system.SysMenuVo>
     */
    public List<SysMenuVo> buildMenus(List<SysMenu> menus) {
        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>(); // 创建一个链表，用于存储SysMenuVo
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo(); // 创建一个SysMenuVo对象
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children)); // 遍历为sysMenuVo的子节点赋值，子节点域是一个链表，存放所有子节点
            }
            sysMenuVoList.add(sysMenuVo); // 将当前SysMenuVo对象添加到链表上
        }
        return sysMenuVoList; // 将链表返回
    }
}
