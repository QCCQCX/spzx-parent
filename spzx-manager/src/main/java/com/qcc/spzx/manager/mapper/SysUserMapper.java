package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.dto.system.SysUserDto;
import com.qcc.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: SysUserMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/10 18:49
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface SysUserMapper {
    /**
     * @param userName
     * @return void
     * @title selectUserInfoByUserName
     * @description 通过用户名查询用户信息
     * @author quchenxi
     * @date 2024/1/10 19:22
     */
    SysUser selectUserInfoByUserName(String userName);

    /**
     * @title findByPage
     * @description 用户条件分页查询
     * @author quchenxi
     * @date 2024/1/12 14:02
     * @param sysUserDto
     * @return java.util.List<com.qcc.spzx.model.entity.system.SysUser>
     */
    List<SysUser> findByPage(SysUserDto sysUserDto);


    /**
     * @title save
     * @description 用户添加
     * @author quchenxi
     * @date 2024/1/12 15:16
     * @param sysUser
     * @return void
     */
    void save(SysUser sysUser);

    /**
     * @title update
     * @description 用户修改
     * @author quchenxi
     * @date 2024/1/12 15:20
     * @param sysUser
     * @return void
     */
    void update(SysUser sysUser);

    /**
     * @title delete
     * @description 用户逻辑删除
     * @author quchenxi
     * @date 2024/1/12 15:32
     * @param userId
     * @return void
     */
    void delete(Long userId);
}
