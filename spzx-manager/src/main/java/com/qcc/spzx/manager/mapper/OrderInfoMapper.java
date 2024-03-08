package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.dto.order.OrderStatisticsDto;
import com.qcc.spzx.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: OrderInfoMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/20 19:40
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface OrderInfoMapper {

    /**
     * @title selectStatisticsByDate
     * @description 根据日期查询交易总金额
     * @author quchenxi
     * @date 2024/1/20 19:53
     * @param createDate
     * @return com.qcc.spzx.model.entity.order.OrderStatistics
     */
    OrderStatistics selectStatisticsByDate(String createDate);

}
