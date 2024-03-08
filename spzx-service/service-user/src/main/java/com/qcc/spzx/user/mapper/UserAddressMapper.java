package com.qcc.spzx.user.mapper;

import com.qcc.spzx.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: UserAddressMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/2/4 22:20
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface UserAddressMapper {
    /**
     * @title findUserAddressList
     * @description 用户地址列表
     * @author quchenxi
     * @date 2024/2/4 22:51
     * @param userId
     * @return java.util.List<com.qcc.spzx.model.entity.user.UserAddress>
     */
    List<UserAddress> findUserAddressList(Long userId);

    /**
     * @title getUserAddress
     * @description 根据id获取地址信息
     * @author quchenxi
     * @date 2024/2/5 20:38
     * @param id
     * @return com.qcc.spzx.model.entity.user.UserAddress
     */
    UserAddress getUserAddress(Long id);
}
