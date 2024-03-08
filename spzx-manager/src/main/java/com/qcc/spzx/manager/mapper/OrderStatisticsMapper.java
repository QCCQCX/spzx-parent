package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.dto.order.OrderStatisticsDto;
import com.qcc.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: OrderStatisticsMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/20 19:41
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface OrderStatisticsMapper {

    /**
     * @title insert
     * @description 订单统计添加
     * @author quchenxi
     * @date 2024/1/20 20:01
     * @param orderStatistics
     * @return void
     */
    void insert(OrderStatistics orderStatistics);

    /**
     * @title selectList
     * @description 根据条件查询订单统计信息
     * @author quchenxi
     * @date 2024/1/20 20:41
     * @param orderStatisticsDto
     * @return java.util.List<com.qcc.spzx.model.entity.order.OrderStatistics>
     */
    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}
