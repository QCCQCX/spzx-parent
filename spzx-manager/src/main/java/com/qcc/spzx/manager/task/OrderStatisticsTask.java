package com.qcc.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import com.qcc.spzx.manager.mapper.OrderInfoMapper;
import com.qcc.spzx.manager.mapper.OrderStatisticsMapper;
import com.qcc.spzx.model.entity.order.OrderStatistics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @ClassName: OrderStatisticsTask
 * @Description: 定时任务类
 * @Date 2024/1/20 19:14
 * @Author quchenxi
 * @Version 1.0
 */
@Component
public class OrderStatisticsTask {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    /**
     * @title orderTotalAmountStatistics
     * @description 每天凌晨2点，查询前一天统计数据，并添加到统计结果表里
     * @author quchenxi
     * @date 2024/1/20 19:33
     * @param
     * @return void
     */
//    @Scheduled(cron = "0 0 2 * * ? ")
    @Scheduled(cron = "0/30 * * * * ? ") // 每30秒执行一次，供测试使用
    public void orderTotalAmountStatistics() {
        // 获取前一天日期
        String createDate = DateUtil.offsetDay(new Date(), -1).toString("yyyy-MM-dd");

        // 统计前一天交易金额
        OrderStatistics orderStatistics = orderInfoMapper.selectStatisticsByDate(createDate);

        // 添加到统计结果表中
        if (orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics);
        }

    }
}
