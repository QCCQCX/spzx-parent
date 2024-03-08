package com.qcc.spzx.manager.controller;

import com.qcc.spzx.manager.service.OrderInfoService;
import com.qcc.spzx.model.dto.order.OrderStatisticsDto;
import com.qcc.spzx.model.entity.order.OrderStatistics;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.model.vo.order.OrderStatisticsVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: OrderInfoController
 * @Description: 订单信息接口
 * @Date 2024/1/20 20:14
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "订单信息接口")
@RestController
@RequestMapping("/admin/order/orderInfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * @title getOrderStatisticsData
     * @description 获取订单统计信息
     * @author quchenxi
     * @date 2024/1/20 20:25
     * @param orderStatisticsDto
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "获取订单统计信息")
    @PostMapping("/getOrderStatisticsData")
    public Result getOrderStatisticsData(@RequestBody OrderStatisticsDto orderStatisticsDto) {
        OrderStatisticsVo orderStatisticsVo = orderInfoService.getOrderStatisticsData(orderStatisticsDto);
        return Result.build(orderStatisticsVo, ResultCodeEnum.SUCCESS);
    }
}
