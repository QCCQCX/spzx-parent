package com.qcc.spzx.order.mapper;

import com.qcc.spzx.model.entity.order.OrderInfo;
import com.qcc.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: OrderInfoMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/2/4 22:36
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface OrderInfoMapper {
    /**
     * @title save
     * @description 订单添加
     * @author quchenxi
     * @date 2024/2/5 20:10
     * @param orderInfo
     * @return void
     */
    void save(OrderInfo orderInfo);

    /**
     * @title getById
     * @description 根据id获取订单数据
     * @author quchenxi
     * @date 2024/2/6 10:57
     * @param orderId
     * @return com.qcc.spzx.model.entity.order.OrderInfo
     */
    OrderInfo getById(Long orderId);

    /**
     * @title findUserPage
     * @description 根据用户id和订单状态查询订单信息列表
     * @author quchenxi
     * @date 2024/2/6 14:42
     * @param userId
     * @param orderStatus
     * @return java.util.List<com.qcc.spzx.model.entity.order.OrderInfo>
     */
    List<OrderInfo> findUserPage(Long userId, Integer orderStatus);
}
