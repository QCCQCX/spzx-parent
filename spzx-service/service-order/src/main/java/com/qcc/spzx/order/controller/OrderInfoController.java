package com.qcc.spzx.order.controller;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.model.dto.h5.OrderInfoDto;
import com.qcc.spzx.model.entity.order.OrderInfo;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.model.vo.h5.TradeVo;
import com.qcc.spzx.order.service.OrderInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bouncycastle.est.ESTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: OrderInfoController
 * @Description: 订单管理接口
 * @Date 2024/2/4 22:34
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "订单管理接口")
@RestController
@RequestMapping("/api/order/orderInfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * @title trade
     * @description 结算
     * @author quchenxi
     * @date 2024/2/5 15:25
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "结算")
    @GetMapping("/auth/trade")
    public Result trade() {
        TradeVo tradeVo = orderInfoService.getTrade();
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title submitOrder
     * @description 提交订单
     * @author quchenxi
     * @date 2024/2/5 19:32
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "提交订单")
    @PostMapping("/auth/submitOrder")
    public Result submitOrder(@RequestBody OrderInfoDto orderInfoDto) {
        Long orderId = orderInfoService.submitOrder(orderInfoDto);
        return Result.build(orderId, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title getOrderInfo
     * @description 获取订单数据
     * @author quchenxi
     * @date 2024/2/6 10:54
     */
    @Operation(summary = "获取订单数据")
    @GetMapping("auth/{orderId}")
    public Result getOrderInfo(@PathVariable("orderId") Long orderId) {
        OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title buy
     * @description 立即购买
     * @author quchenxi
     * @date 2024/2/6 11:10
     */
    @Operation(summary = "立即购买")
    @GetMapping("/auth/buy/{skuId}")
    public Result buy(@PathVariable Long skuId) {
        TradeVo tradeVo = orderInfoService.buy(skuId);
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title list
     * @description 获取订单分页列表
     * @author quchenxi
     * @date 2024/2/6 14:22
     * @param page
     * @param limit
     * @param orderStatus 订单状态，默认为空，有则根据状态查询
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "获取订单分页列表")
    @GetMapping("/auth/{page}/{limit}")
    public Result list(@PathVariable Integer page,
                       @PathVariable Integer limit,
                       @RequestParam(required = false, defaultValue = "") Integer orderStatus) {
        PageInfo<OrderInfo> pageInfo = orderInfoService.findOrderByPage(page, limit, orderStatus);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }
}
