package com.qcc.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qcc.spzx.manager.mapper.SysRoleMapper;
import com.qcc.spzx.manager.service.SysRoleService;
import com.qcc.spzx.model.dto.system.SysRoleDto;
import com.qcc.spzx.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysRoleSerivceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/11 20:05
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * @title findByPage
     * @description 角色列表
     * @author quchenxi
     * @date 2024/1/11 20:28
     * @param sysRoleDto
     * @param current
     * @param limit
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.system.SysRole>
     */
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer current, Integer limit) {
        // 设置分页参数
        PageHelper.startPage(current, limit);
        // 根据条件查询所有数据
        List<SysRole> list = sysRoleMapper.findByPage(sysRoleDto);
        // 封装为PageInfo对象，并返回
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * @title saveSysRole
     * @description 角色添加
     * @author quchenxi
     * @date 2024/1/11 21:16
     * @param sysRole
     * @return void
     */
    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.save(sysRole);
    }

    /**
     * @title updateSysRole
     * @description 角色修改
     * @author quchenxi
     * @date 2024/1/12 11:54
     * @param sysRole
     * @return void
     */
    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    /**
     * @title deleteById
     * @description 角色逻辑删除
     * @author quchenxi
     * @date 2024/1/12 13:22
     * @param roleId
     * @return void
     */
    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.delete(roleId);
    }

    /**
     * @param
     * @param userId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @title findAll
     * @description 查询所有角色
     * @author quchenxi
     * @date 2024/1/12 23:07
     */
    @Override
    public Map<String, Object> findAll(Long userId) {
        // 查询所有角色
        List<SysRole> allRolesList = sysRoleMapper.findAll();

        // 查询用户所有分配角色id
        List<Long> userRolesList = sysRoleMapper.selectRoleIdsByUserId(userId);

        Map<String, Object> map = new HashMap<>();
        map.put("allRolesList", allRolesList); // 将所有角色列表放到map中
        map.put("userRolesList", userRolesList); // 将用户所有分配角色id放到map中

        return map;
    }

}
