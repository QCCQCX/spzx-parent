package com.qcc.spzx.user.controller;

import com.qcc.spzx.model.entity.user.UserAddress;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.user.service.UserAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: UserAddressController
 * @Description: 用户收货地址接口
 * @Date 2024/2/4 22:18
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "用户收货地址接口")
@RestController
@RequestMapping("api/user/userAddress")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    /**
     * @title findUserAddressList
     * @description 用户地址列表
     * @author quchenxi
     * @date 2024/2/4 22:48
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "用户地址列表")
    @GetMapping("auth/findUserAddressList")
    private Result findUserAddressList() {
        List<UserAddress> list = userAddressService.findUserAddressList();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.entity.user.UserAddress
     * @title getUserAddress
     * @description 根据id获取地址信息
     * @author quchenxi
     * @date 2024/2/5 20:35
     */
    @Operation(summary = "根据id获取地址信息")
    @GetMapping("/getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable Long id) {
        System.out.println("2");
        return userAddressService.getUserAddress(id);
    }
}
