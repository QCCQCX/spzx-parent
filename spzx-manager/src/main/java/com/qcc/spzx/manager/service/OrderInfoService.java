package com.qcc.spzx.manager.service;

import com.qcc.spzx.manager.mapper.OrderInfoMapper;
import com.qcc.spzx.model.dto.order.OrderStatisticsDto;
import com.qcc.spzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: OrderInfoService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/20 20:16
 * @Author quchenxi
 * @Version 1.0
 */
public interface OrderInfoService {

    /**
     * @title getOrderStatisticsData
     * @description 根据订单统计条件查询订单统计信息
     * @author quchenxi
     * @date 2024/1/20 20:24
     * @param orderStatisticsDto
     * @return com.qcc.spzx.model.vo.order.OrderStatisticsVo
     */
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
