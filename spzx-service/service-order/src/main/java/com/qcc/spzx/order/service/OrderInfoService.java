package com.qcc.spzx.order.service;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.model.dto.h5.OrderInfoDto;
import com.qcc.spzx.model.entity.order.OrderInfo;
import com.qcc.spzx.model.vo.h5.TradeVo;

/**
 * @ClassName: OrderInfoService
 * @Description: 此处输入类描述信息
 * @Date 2024/2/4 22:35
 * @Author quchenxi
 * @Version 1.0
 */
public interface OrderInfoService {
    /**
     * @title getTrade
     * @description 结算
     * @author quchenxi
     * @date 2024/2/5 15:26
     * @param
     * @return com.qcc.spzx.model.vo.h5.TradeVo
     */
    TradeVo getTrade();

    /**
     * @title submitOrder
     * @description 提交订单
     * @author quchenxi
     * @date 2024/2/5 19:33
     * @param orderInfoDto
     * @return java.lang.Long
     */
    Long submitOrder(OrderInfoDto orderInfoDto);

    /**
     * @title getOrderInfo
     * @description 获取订单数据
     * @author quchenxi
     * @date 2024/2/6 10:56
     * @param orderId
     * @return com.qcc.spzx.model.entity.order.OrderInfo
     */
    OrderInfo getOrderInfo(Long orderId);

    /**
     * @title buy
     * @description 立即购买
     * @author quchenxi
     * @date 2024/2/6 11:14
     * @param skuId
     * @return com.qcc.spzx.model.vo.h5.TradeVo
     */
    TradeVo buy(Long skuId);

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
    PageInfo<OrderInfo> findOrderByPage(Integer page, Integer limit, Integer orderStatus);
}
