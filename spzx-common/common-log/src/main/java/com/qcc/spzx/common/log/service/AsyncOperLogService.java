package com.qcc.spzx.common.log.service;

import com.qcc.spzx.model.entity.system.SysOperLog;

/**
 * @ClassName: AsyncOperLogService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/22 16:28
 * @Author quchenxi
 * @Version 1.0
 */
public interface AsyncOperLogService {
    /**
     * @title saveSysOperLog
     * @description 保存日志数据
     * @author quchenxi
     * @date 2024/1/22 16:34
     * @param sysOperLog
     * @return void
     */
    void saveSysOperLog(SysOperLog sysOperLog);
}
