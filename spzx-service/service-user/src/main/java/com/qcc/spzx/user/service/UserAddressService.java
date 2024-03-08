package com.qcc.spzx.user.service;

import com.qcc.spzx.model.entity.user.UserAddress;

import java.util.List;

/**
 * @ClassName: UserAddressService
 * @Description: 此处输入类描述信息
 * @Date 2024/2/4 22:21
 * @Author quchenxi
 * @Version 1.0
 */
public interface UserAddressService {
    /**
     * @title findUserAddressList
     * @description 用户地址列表
     * @author quchenxi
     * @date 2024/2/4 22:50
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.user.UserAddress>
     */
    List<UserAddress> findUserAddressList();

    /**
     * @title getUserAddress
     * @description 根据id获取地址信息
     * @author quchenxi
     * @date 2024/2/5 20:37
     * @param id
     * @return com.qcc.spzx.model.entity.user.UserAddress
     */
    UserAddress getUserAddress(Long id);
}
