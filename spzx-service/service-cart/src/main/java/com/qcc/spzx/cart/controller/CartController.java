package com.qcc.spzx.cart.controller;

import com.qcc.spzx.cart.service.CartService;
import com.qcc.spzx.model.entity.h5.CartInfo;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: CartController
 * @Description: 购物车接口
 * @Date 2024/2/3 11:10
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "购物车接口")
@RestController
@RequestMapping("/api/order/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    /**
     * @title addToCart
     * @description 添加购物车
     * @author quchenxi
     * @date 2024/2/3 11:16
     * @param skuId 添加购物车商品的sku的id值
     * @param skuNum 添加购物车商品的数量
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "添加购物车")
    @GetMapping("/auth/addToCart/{skuId}/{skuNum}")
    public Result addToCart(@PathVariable Long skuId,
                            @PathVariable Integer skuNum) {
        cartService.addToCart(skuId, skuNum);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title cartList
     * @description 购物车列表
     * @author quchenxi
     * @date 2024/2/4 20:00
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "购物车列表")
    @GetMapping("/auth/cartList")
    public Result cartList() {
        List<CartInfo> cartInfoList = cartService.getCartList();
        return Result.build(cartInfoList, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title deleteCart
     * @description 删除购物车商品
     * @author quchenxi
     * @date 2024/2/4 20:34
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "删除购物车商品")
    @DeleteMapping("/auth/deleteCart/{skuId}")
    public Result deleteCart(@PathVariable("skuId") Long skuId) {
        cartService.deleteCart(skuId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title checkCart
     * @description 购物车商品选中状态
     * @author quchenxi
     * @date 2024/2/4 20:50
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "购物车商品选中状态")
    @GetMapping("/auth/checkCart/{skuId}/{idChecked}")
    public Result checkCart(@PathVariable("skuId") Long skuId,
                            @PathVariable("idChecked") Integer isChecked) {
        cartService.checkCart(skuId, isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title allCheckCart
     * @description 购物车全部商品选中状态
     * @author quchenxi
     * @date 2024/2/4 21:44
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "购物车全部商品选中状态")
    @GetMapping("/auth/allCheckCart/{isChecked}")
    public Result allCheckCart(@PathVariable("isChecked") Integer isChecked) {
        cartService.allCheckCart(isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title clearCart
     * @description 清空购物车
     * @author quchenxi
     * @date 2024/2/4 22:09
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "清空购物车")
    @GetMapping("/auth/clearCart")
    public Result clearCart() {
        cartService.clearCart();
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title getAllChecked
     * @description 获取购物车选中商品列表（供远程调用使用）
     * @author quchenxi
     * @date 2024/2/5 15:04
     * @param
     * @return java.util.List
     */
    @Operation(summary = "获取购物车选中商品列表")
    @GetMapping("/auth/getAllChecked")
    public List<CartInfo> getAllChecked() {
        List<CartInfo> list = cartService.getAllChecked();
        return list;
    }

    /**
     * @title deleteChecked
     * @description 删除提交订单后的购物车中的商品（供远程调用使用）
     * @author quchenxi
     * @date 2024/2/5 20:59
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "删除提交订单后的购物车中的商品")
    @GetMapping("/auth/deleteChecked")
    public Result deleteChecked() {
        cartService.deleteChecked();
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
