package com.qcc.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.model.dto.system.AssignRoleDto;
import com.qcc.spzx.model.dto.system.LoginDto;
import com.qcc.spzx.model.dto.system.SysUserDto;
import com.qcc.spzx.model.entity.system.SysUser;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.system.LoginVo;

/**
 * @ClassName: SysUserService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/10 18:48
 * @Author quchenxi
 * @Version 1.0
 */
public interface SysUserService {
    /**
     * @title login
     * @description 用户登录
     * @author quchenxi
     * @date 2024/1/10 19:04
     * @param loginDto
     * @return com.qcc.spzx.model.vo.system.LoginVo
     */
    LoginVo login(LoginDto loginDto);


    /**
     * @title getUserInfo
     * @description 根据token获取当前登录用户信息
     * @author quchenxi
     * @date 2024/1/11 15:40
     * @param token
     * @return com.qcc.spzx.model.vo.common.Result
     */
    SysUser getUserInfo(String token);

    /**
     * @title logout
     * @description 退出登录
     * @author quchenxi
     * @date 2024/1/10 23:14
     * @param token
     * @return void
     */
    void logout(String token);

    /**
     * @title findByPage
     * @description 用户条件分页查询
     * @author quchenxi
     * @date 2024/1/12 13:57
     * @param pageNum
     * @param pageSize
     * @param sysUserDto
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.system.SysUser>
     */
    PageInfo<SysUser> findByPage(Integer pageNum, Integer pageSize, SysUserDto sysUserDto);

    /**
     * @title saveSysUser
     * @description 用户添加
     * @author quchenxi
     * @date 2024/1/12 14:40
     * @param sysUser
     * @return void
     */
    void saveSysUser(SysUser sysUser);

    /**
     * @title updateSysUser
     * @description 用户修改
     * @author quchenxi
     * @date 2024/1/12 15:19
     * @param sysUser
     * @return void
     */
    void updateSysUser(SysUser sysUser);

    /**
     * @title deleteById
     * @description 用户逻辑删除
     * @author quchenxi
     * @date 2024/1/12 15:31
     * @param userId
     * @return void
     */
    void deleteById(Long userId);

    /**
     * @title doAssign
     * @description 用户分配角色
     * @author quchenxi
     * @date 2024/1/12 23:20
     * @param assignRoleDto
     * @return void
     */
    void doAssign(AssignRoleDto assignRoleDto);
}
