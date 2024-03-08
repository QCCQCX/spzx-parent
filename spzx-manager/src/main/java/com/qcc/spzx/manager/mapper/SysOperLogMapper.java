package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: SysOperLogMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/22 16:36
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface SysOperLogMapper {
    /**
     * @title insert
     * @description 日志信息添加
     * @author quchenxi
     * @date 2024/1/22 16:39
     * @param sysOperLog
     * @return void
     */
    void insert(SysOperLog sysOperLog);
}
