package com.qcc.spzx.cart.service.impl;

import com.alibaba.fastjson2.JSON;
import com.qcc.spzx.cart.service.CartService;
import com.qcc.spzx.feign.product.ProductFeignClient;
import com.qcc.spzx.model.entity.h5.CartInfo;
import com.qcc.spzx.model.entity.product.ProductSku;
import com.qcc.spzx.model.entity.user.UserInfo;
import com.qcc.spzx.utils.AuthContextUtil;
import org.checkerframework.checker.units.qual.C;
import org.ehcache.core.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: CartServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/2/3 11:12
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * @title addToCart
     * @description 添加购物车
     * @author quchenxi
     * @date 2024/2/3 11:18
     * @param skuId
     * @param skuNum
     * @return void
     */
    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        // 获取当前登录用户的id（用于redis的hash类型数据的key值）
        Long userId = AuthContextUtil.getUserInfo().getId();

        // 构建hash类型数据的key值
        String cartKey = this.getCartKey(userId);

        // 从redis中获取购物车商品数据
        // key: userId, filed: skuId, value: CartInfo
        Object cartInfoObj = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));

        // 根据获取的redis中的购物车数据，封装CartInfo对象，用于更新redis中购物车数据
        CartInfo cartInfo = null;
        if (cartInfoObj != null) { // 若购物车中存在数据，更改数量
            // 将cartInfoObj转换成cartInfo
            cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            // 更改数量
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            // 设置选中属性值为选中状态
            cartInfo.setIsChecked(1);
            // 设置修改时间为当前时间
            cartInfo.setUpdateTime(new Date());
        } else { // 若购物车中不存在数据，添加
            // new对象，用于封装购物车商品数据
            cartInfo = new CartInfo();
            // 通过远程调用实现：根据skuId获取商品sku信息
            ProductSku productSku = productFeignClient.getBySkuId(skuId);
            // 封装数据
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
        }

        // 更新redis中的购物车数据
        redisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
    }

    /**
     * @title getCartList
     * @description 购物车列表
     * @author quchenxi
     * @date 2024/2/4 20:05
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.h5.CartInfo>
     */
    @Override
    public List<CartInfo> getCartList() {
        // 从threadLocal中获取用户信息，根据userId构建key值
        Long userId = AuthContextUtil.getUserInfo().getId();
        String key = this.getCartKey(userId);

        // 根据key从redis获取所有filed的value值，转换成cartInfo
        List<Object> valueList = redisTemplate.opsForHash().values(key);
        if (!CollectionUtils.isEmpty(valueList)) {
            List<CartInfo> cartInfoList = valueList.stream().map(cartInfoObj ->
                            JSON.parseObject(cartInfoObj.toString(), CartInfo.class)) // 转换
                    .sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime())) // 做排序
                    .collect(Collectors.toList());
            return cartInfoList;
        }

        // 若为空，则返回空集合
        return new ArrayList<>();
    }

    /**
     * @title deleteCart
     * @description 删除购物车商品
     * @author quchenxi
     * @date 2024/2/4 20:35
     * @param skuId
     * @return void
     */
    @Override
    public void deleteCart(Long skuId) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);
        // 在redis中根据hash类型数据的key和field进行删除
        redisTemplate.opsForHash().delete(cartKey, String.valueOf(skuId));
    }

    /**
     * @title checkCart
     * @description 购物车商品选中状态
     * @author quchenxi
     * @date 2024/2/4 20:55
     * @param skuId
     * @param isChecked
     * @return void
     */
    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        // 判断redis中是否包含指定的value，传入参数key和field
        Boolean hasKey = redisTemplate.opsForHash().hasKey(cartKey, String.valueOf(skuId));
        if (hasKey) {
            // 根据key和field获取value，并转换成CartInfo类型
            String cartInfoString = redisTemplate.opsForHash()
                    .get(cartKey, String.valueOf(skuId)).toString();
            CartInfo cartInfo = JSON.parseObject(cartInfoString, CartInfo.class);

            // 更新选中状态
            cartInfo.setIsChecked(isChecked);

            // 更改redis中的hash类型数据的value
            redisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
        }

    }

    /**
     * @title allCheckCart
     * @description 购物车全部商品选中状态
     * @author quchenxi
     * @date 2024/2/4 21:47
     * @param isChecked
     * @return void
     */
    @Override
    public void allCheckCart(Integer isChecked) {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        // 获取redis中指定key的所有field的value值
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        if (!CollectionUtils.isEmpty(objectList)) {
            List<CartInfo> cartInfoList = objectList.stream().map(object ->
                            JSON.parseObject(object.toString(), CartInfo.class)) // 转换为CartInfo类型
                    .collect(Collectors.toList());
            // 更新每一个cartInfo的选中状态值
            cartInfoList.forEach(cartInfo -> {
                cartInfo.setIsChecked(isChecked);
                // 放回到redis中
                redisTemplate.opsForHash().put(cartKey,
                        String.valueOf(cartInfo.getSkuId()),
                        JSON.toJSONString(cartInfo));
            });
        }
    }

    /**
     * @title clearCart
     * @description 清空购物车
     * @author quchenxi
     * @date 2024/2/4 22:10
     * @param
     * @return void
     */
    @Override
    public void clearCart() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        // 删除指定key所有数据
        redisTemplate.delete(cartKey);
    }

    /**
     * @title getAllChecked
     * @description 获取购物车选中商品列表
     * @author quchenxi
     * @date 2024/2/5 15:05
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.h5.CartInfo>
     */
    @Override
    public List<CartInfo> getAllChecked() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        List<Object> valueList = redisTemplate.opsForHash().values(cartKey);
        if (!CollectionUtils.isEmpty(valueList)) {
            List<CartInfo> cartInfoList = valueList.stream().map(object -> JSON.parseObject(object.toString(), CartInfo.class))
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1) // 过滤器，过滤出满足指定条件的元素
                    .collect(Collectors.toList());
            return cartInfoList;
        }

        return new ArrayList<>();
    }

    /**
     * @title deleteChecked
     * @description 删除提交订单后的购物车中的商品
     * @author quchenxi
     * @date 2024/2/5 21:01
     * @param
     * @return void
     */
    @Override
    public void deleteChecked() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        // 根据key获取redis中所有value值
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        objectList.stream().map(object -> JSON.parseObject(object.toString(), CartInfo.class)) // 类型转换
                .filter(cartInfo -> cartInfo.getIsChecked() == 1) // 过滤出当前选中状态的购物车中的商品
                .forEach(cartInfo -> redisTemplate.opsForHash() // 遍历购物车
                        .delete(cartKey, String.valueOf(cartInfo.getSkuId()))); // 删除购物车中该商品
    }

    /**
     * @title getCartKey
     * @description 使用用户id组成redis中hash类型数据的key，并返回
     * @author quchenxi
     * @date 2024/2/3 11:27
     * @param userId
     * @return java.lang.String
     */
    private String getCartKey(Long userId) {
        return "user:cart:" + userId;
    }
}
