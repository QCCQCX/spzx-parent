package com.qcc.spzx.order.mapper;

import com.qcc.spzx.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: OrderItemMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/2/5 19:28
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface OrderItemMapper {
    /**
     * @title save
     * @description 订单项添加
     * @author quchenxi
     * @date 2024/2/5 20:14
     * @param orderItem
     * @return void
     */
    void save(OrderItem orderItem);

    /**
     * @title findByOrderId
     * @description 根据订单id查询所有订单项
     * @author quchenxi
     * @date 2024/2/6 14:42
     * @param id
     * @return java.util.List<com.qcc.spzx.model.entity.order.OrderItem>
     */
    List<OrderItem> findByOrderId(Long id);
}
