package com.qcc.spzx.order.mapper;

import com.qcc.spzx.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: OrderLogMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/2/5 19:28
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface OrderLogMapper {
    /**
     * @title save
     * @description 订单日志添加
     * @author quchenxi
     * @date 2024/2/5 20:16
     * @param orderLog
     * @return void
     */
    void save(OrderLog orderLog);
}
