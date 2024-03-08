package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.dto.system.SysRoleDto;
import com.qcc.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: SysRoleMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/11 20:06
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface SysRoleMapper {

    /**
     * @title findByPage
     * @description 角色列表
     * @author quchenxi
     * @date 2024/1/11 20:33
     * @param sysRoleDto
     * @return java.util.List<com.qcc.spzx.model.entity.system.SysRole>
     */
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    /**
     * @title save
     * @description 角色添加
     * @author quchenxi
     * @date 2024/1/11 21:16
     * @param sysRole
     * @return void
     */
    void save(SysRole sysRole);

    /**
     * @title update
     * @description 角色修改
     * @author quchenxi
     * @date 2024/1/12 11:54
     * @param sysRole
     * @return void
     */
    void update(SysRole sysRole);

    /**
     * @title delete
     * @description 角色逻辑删除
     * @author quchenxi
     * @date 2024/1/12 13:23
     * @param roleId
     * @return void
     */
    void delete(Long roleId);

    /**
     * @title findAll
     * @description 查询所有角色列表
     * @author quchenxi
     * @date 2024/1/12 23:11
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.system.SysRole>
     */
    List<SysRole> findAll();

    /**
     * @title selectRoleIdsByUserId
     * @description 根据用户id查询用户所有分配角色id
     * @author quchenxi
     * @date 2024/1/13 00:00
     * @param userId
     * @return java.util.List<java.lang.Long>
     */
    List<Long> selectRoleIdsByUserId(Long userId);
}
