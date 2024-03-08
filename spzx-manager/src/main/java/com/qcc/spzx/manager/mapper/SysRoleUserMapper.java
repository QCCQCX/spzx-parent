package com.qcc.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: SysRoleUserMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/12 22:59
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface SysRoleUserMapper {
    /**
     * @title deleteByUserId
     * @description 用户角色删除
     * @author quchenxi
     * @date 2024/1/12 23:31
     * @param userId
     * @return void
     */
    void deleteByUserId(Long userId);

    /**
     * @title doAssign
     * @description 用户分配角色
     * @author quchenxi
     * @date 2024/1/12 23:38
     * @param userId
     * @param roleId
     * @return void
     */
    void doAssign(Long userId, Long roleId);
}
