package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.dto.system.AssignMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: SysRoleMenuMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/13 13:12
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface SysRoleMenuMapper {

    /**
     * @title findSysRoleMenuByRoleId
     * @description 查询角色分配的菜单id列表
     * @author quchenxi
     * @date 2024/1/13 13:28
     * @param roleId
     * @return java.util.List<java.lang.Long>
     */
    List<Long> findSysRoleMenuByRoleId(Long roleId);

    /**
     * @title deleteByRoleId
     * @description 删除角色分配的所有菜单
     * @author quchenxi
     * @date 2024/1/13 14:07
     * @param roleId
     * @return void
     */
    void deleteByRoleId(Long roleId);

    /**
     * @title doAssign
     * @description 角色分配菜单
     * @author quchenxi
     * @date 2024/1/13 14:08
     * @param assignMenuDto
     * @return void
     */
    void doAssign(AssignMenuDto assignMenuDto);

    /**
     * @title updateSysRoleMenuIsHalf
     * @description 将菜单的isHalf值修改为1
     * @author quchenxi
     * @date 2024/1/13 17:06
     * @param id
     * @return void
     */
    void updateSysRoleMenuIsHalf(Long id);
}
