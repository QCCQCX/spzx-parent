package com.qcc.spzx.cart.service;

import com.qcc.spzx.model.entity.h5.CartInfo;

import java.util.List;

/**
 * @ClassName: CartService
 * @Description: 此处输入类描述信息
 * @Date 2024/2/3 11:11
 * @Author quchenxi
 * @Version 1.0
 */
public interface CartService {
    /**
     * @title addToCart
     * @description 添加购物车
     * @author quchenxi
     * @date 2024/2/3 11:18
     * @param skuId
     * @param skuNum
     * @return void
     */
    void addToCart(Long skuId, Integer skuNum);

    /**
     * @title getCartList
     * @description 购物车列表
     * @author quchenxi
     * @date 2024/2/4 20:04
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.h5.CartInfo>
     */
    List<CartInfo> getCartList();

    /**
     * @title deleteCart
     * @description 删除购物车商品
     * @author quchenxi
     * @date 2024/2/4 20:35
     * @param skuId
     * @return
     */
     void deleteCart(Long skuId);

    /**
     * @title checkCart
     * @description 购物车商品选中状态
     * @author quchenxi
     * @date 2024/2/4 20:54
     * @param skuId
     * @param isChecked
     * @return void
     */
    void checkCart(Long skuId, Integer isChecked);

    /**
     * @title allCheckCart
     * @description 购物车全部商品选中状态
     * @author quchenxi
     * @date 2024/2/4 21:47
     * @param isChecked
     * @return void
     */
    void allCheckCart(Integer isChecked);

    /**
     * @title clearCart
     * @description 清空购物车
     * @author quchenxi
     * @date 2024/2/4 22:10
     * @param
     * @return void
     */
    void clearCart();

    /**
     * @title getAllChecked
     * @description 获取购物车选中商品列表
     * @author quchenxi
     * @date 2024/2/5 15:05
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.h5.CartInfo>
     */
    List<CartInfo> getAllChecked();

    /**
     * @title deleteChecked
     * @description 删除提交订单后的购物车中的商品
     * @author quchenxi
     * @date 2024/2/5 21:00
     * @param
     * @return void
     */
    void deleteChecked();
}
