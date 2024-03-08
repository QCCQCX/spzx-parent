package com.qcc.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.qcc.spzx.manager.mapper.OrderInfoMapper;
import com.qcc.spzx.manager.mapper.OrderStatisticsMapper;
import com.qcc.spzx.manager.service.OrderInfoService;
import com.qcc.spzx.model.dto.order.OrderStatisticsDto;
import com.qcc.spzx.model.entity.order.OrderStatistics;
import com.qcc.spzx.model.vo.order.OrderStatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: OrderInfoServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/20 20:17
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    /**
     * @title getOrderStatisticsData
     * @description 根据订单统计条件查询订单统计信息
     * @author quchenxi
     * @date 2024/1/20 20:25
     * @param orderStatisticsDto
     * @return com.qcc.spzx.model.vo.order.OrderStatisticsVo
     */
    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        // 根据条件查询订单统计信息
        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(orderStatisticsDto);

        // 遍历统计信息list，封装所有日期和总金额到两个新的list
        List<String> dateList = orderStatisticsList.stream()
                .map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd"))
                .collect(Collectors.toList());
        List<BigDecimal> totalAmountList = orderStatisticsList.stream()
                .map(orderStatistics -> orderStatistics.getTotalAmount())
                .collect(Collectors.toList());

        // 将两个list封装为OrderStatisticsVo，并返回
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(totalAmountList);
        return orderStatisticsVo;
    }
}
