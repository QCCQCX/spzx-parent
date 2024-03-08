package com.qcc.spzx.manager.service.impl;

import com.qcc.spzx.common.log.service.AsyncOperLogService;
import com.qcc.spzx.manager.mapper.SysOperLogMapper;
import com.qcc.spzx.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: AsyncOperLogServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/22 16:34
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    /**
     * @title saveSysOperLog
     * @description 保存日志数据
     * @author quchenxi
     * @date 2024/1/22 16:34
     * @param sysOperLog
     * @return void
     */
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}
