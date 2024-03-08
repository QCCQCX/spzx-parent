package com.qcc.spzx.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qcc.spzx.common.exception.QccException;
import com.qcc.spzx.feign.cart.CartFeignClient;
import com.qcc.spzx.feign.product.ProductFeignClient;
import com.qcc.spzx.feign.user.UserFeignClient;
import com.qcc.spzx.model.dto.h5.OrderInfoDto;
import com.qcc.spzx.model.entity.h5.CartInfo;
import com.qcc.spzx.model.entity.order.OrderInfo;
import com.qcc.spzx.model.entity.order.OrderItem;
import com.qcc.spzx.model.entity.order.OrderLog;
import com.qcc.spzx.model.entity.product.ProductSku;
import com.qcc.spzx.model.entity.user.UserAddress;
import com.qcc.spzx.model.entity.user.UserInfo;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.model.vo.h5.TradeVo;
import com.qcc.spzx.order.mapper.OrderInfoMapper;
import com.qcc.spzx.order.mapper.OrderItemMapper;
import com.qcc.spzx.order.mapper.OrderLogMapper;
import com.qcc.spzx.order.service.OrderInfoService;
import com.qcc.spzx.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: OrderInfoServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/2/4 22:36
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;

    @Autowired
    private CartFeignClient cartFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * @title getTrade
     * @description 结算
     * @author quchenxi
     * @date 2024/2/5 15:27
     * @param
     * @return com.qcc.spzx.model.vo.h5.TradeVo
     */
    @Override
    public TradeVo getTrade() {
        // 远程调用获取购物车中选中的商品列表
        List<CartInfo> cartInfoList = cartFeignClient.getAllChecked();

        // 创建OrderItem集合，将CartInfo集合转换成OrderItem集合
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        for (CartInfo cartInfo : cartInfoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemList.add(orderItem);
        }

        // 获取购物车已选商品总金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            // 总金额 = 总金额 + （单价 * 数量）
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }

        // 封装tradeVo，并返回
        TradeVo tradeVo = new TradeVo();
        tradeVo.setOrderItemList(orderItemList);
        tradeVo.setTotalAmount(totalAmount);
        return tradeVo;
    }

    /**
     * @title submitOrder
     * @description 提交订单
     * @author quchenxi
     * @date 2024/2/5 19:34
     * @param orderInfoDto
     * @return java.lang.Long
     */
    @Override
    public Long submitOrder(OrderInfoDto orderInfoDto) {
        // 获取所有订单项
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();

        // 判空
        if (CollectionUtils.isEmpty(orderItemList)) {
            throw new QccException(ResultCodeEnum.DATA_ERROR);
        }

        // 检测库存量
        for (OrderItem orderItem : orderItemList) {
            // 根据skuId获取sku，通过远程调用实现
            ProductSku productSku = productFeignClient.getBySkuId(orderItem.getSkuId());
            if (productSku == null) {
                throw new QccException(ResultCodeEnum.DATA_ERROR);
            }
            // 比较购买量与库存量
            if (orderItem.getSkuNum().intValue() > productSku.getStockNum().intValue()) {
                throw new QccException(ResultCodeEnum.STOCK_LESS);
            }
        }

        // 添加数据到order_info表（需要远程调用实现：根据用户收货地址id获取用户收货地址信息）
        OrderInfo orderInfo = new OrderInfo(); // 创建OrderInfo对象，用于封装数据
        UserInfo userInfo = AuthContextUtil.getUserInfo(); // 获取当前登录用户信息
        orderInfo.setOrderNo(String.valueOf(System.currentTimeMillis())); // 订单编号（使用当前时间戳）
        orderInfo.setUserId(userInfo.getId()); // 用户id
        orderInfo.setNickName(userInfo.getNickName()); // 用户昵称

        Long userAddressId = orderInfoDto.getUserAddressId(); // 获取用户收货地址id
        UserAddress userAddress = userFeignClient.getUserAddress(userAddressId); // 远程调用实现：根据用户收货地址id获取用户收货地址信息
        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        orderInfo.setReceiverAddress(userAddress.getFullAddress());

        BigDecimal totalAmount = new BigDecimal(0); // 用于计算订单总金额
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setCouponAmount(new BigDecimal(0));
        orderInfo.setOriginalTotalAmount(totalAmount);
        orderInfo.setFeightFee(orderInfoDto.getFeightFee());
        orderInfo.setPayType(2);
        orderInfo.setOrderStatus(0);

        orderInfoMapper.save(orderInfo); // 存入数据库

        // 添加数据到order_item表
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(orderInfo.getId()); // 将每一个订单项的订单id赋值为当前订单id
            orderItemMapper.save(orderItem);
        }

        // 添加数据到order_log表
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setProcessStatus(0);
        orderLog.setNote("提交订单");
        orderLogMapper.save(orderLog);

        // 远程调用实现：把订单商品从购物车中删除
        cartFeignClient.deleteChecked();

        // 返回订单id
        return orderInfo.getId();
    }

    /**
     * @title getOrderInfo
     * @description 获取订单数据
     * @author quchenxi
     * @date 2024/2/6 10:56
     * @param orderId
     * @return com.qcc.spzx.model.entity.order.OrderInfo
     */
    @Override
    public OrderInfo getOrderInfo(Long orderId) {
        return orderInfoMapper.getById(orderId);
    }

    /**
     * @param skuId
     * @return com.qcc.spzx.model.vo.h5.TradeVo
     * @title buy
     * @description 立即购买
     * @author quchenxi
     * @date 2024/2/6 11:14
     */
    @Override
    public TradeVo buy(Long skuId) {
        // 获取sku信息
        ArrayList<OrderItem> orderItemList = new ArrayList<>();
        ProductSku productSku = productFeignClient.getBySkuId(skuId); // 远程调用实现：根据skuId获取sku信息

        // 封装订单项数据
        OrderItem orderItem = new OrderItem(); // 创建订单项对象
        orderItem.setSkuId(skuId);
        orderItem.setSkuName(productSku.getSkuName());
        orderItem.setSkuNum(1);
        orderItem.setSkuPrice(productSku.getSalePrice());
        orderItem.setThumbImg(productSku.getThumbImg());
        orderItemList.add(orderItem); // 将订单项添加到订单项集合

        // 封装交易数据，并返回
        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(productSku.getSalePrice());
        tradeVo.setOrderItemList(orderItemList);
        return tradeVo;
    }

    /**
     * @title findOrderByPage
     * @description 获取订单分页列表
     * @author quchenxi
     * @date 2024/2/6 14:24
     * @param page
     * @param limit
     * @param orderStatus
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.order.OrderInfo>
     */
    @Override
    public PageInfo<OrderInfo> findOrderByPage(Integer page, Integer limit, Integer orderStatus) {
        PageHelper.startPage(page, limit);

        // 查询订单信息
        Long userId = AuthContextUtil.getUserInfo().getId();
        List<OrderInfo> orderInfoList = orderInfoMapper.findUserPage(userId, orderStatus);

        // 获取所有订单项
        orderInfoList.forEach(orderInfo -> {
            // 根据订单id查询所有订单项
            List<OrderItem> orderItemList = orderItemMapper.findByOrderId(orderInfo.getId());
            // 封装到OrderInfo中
            orderInfo.setOrderItemList(orderItemList);
        });

        return new PageInfo<>(orderInfoList);
    }
}
