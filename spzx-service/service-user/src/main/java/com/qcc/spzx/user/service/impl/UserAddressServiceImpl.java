package com.qcc.spzx.user.service.impl;

import com.qcc.spzx.model.entity.user.UserAddress;
import com.qcc.spzx.user.mapper.UserAddressMapper;
import com.qcc.spzx.user.service.UserAddressService;
import com.qcc.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserAddressServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/2/4 22:21
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    /**
     * @title findUserAddressList
     * @description 用户地址列表
     * @author quchenxi
     * @date 2024/2/4 22:50
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.user.UserAddress>
     */
    @Override
    public List<UserAddress> findUserAddressList() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        return userAddressMapper.findUserAddressList(userId);
    }

    /**
     * @title getUserAddress
     * @description 根据id获取地址信息
     * @author quchenxi
     * @date 2024/2/5 20:37
     * @param id
     * @return com.qcc.spzx.model.entity.user.UserAddress
     */
    @Override
    public UserAddress getUserAddress(Long id) {
        return userAddressMapper.getUserAddress(id);
    }
}
