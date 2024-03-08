package com.qcc.spzx.common.log.utils;

import com.alibaba.fastjson.JSON;
import com.qcc.spzx.common.log.annotation.Log;
import com.qcc.spzx.model.entity.system.SysOperLog;
import com.qcc.spzx.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @ClassName: LogUtil
 * @Description: 日志工具类
 * @Date 2024/1/21 20:32
 * @Author quchenxi
 * @Version 1.0
 */
public class LogUtil {
    /**
     * @title afterHandlLog
     * @description 操作执行之后调用，封装日志数据
     * @author quchenxi
     * @date 2024/1/22 16:01
     * @param sysLog
     * @param proceed
     * @param sysOperLog
     * @param status
     * @param errorMsg
     * @return void
     */
    public static void afterHandlLog(Log sysLog, Object proceed,
                                     SysOperLog sysOperLog,
                                     int status,
                                     String errorMsg) {
        if(sysLog.isSaveResponseData()) {
            sysOperLog.setJsonResult(JSON.toJSONString(proceed));
        }
        sysOperLog.setStatus(status);
        sysOperLog.setErrorMsg(errorMsg);
    }

    /**
     * @title beforeHandleLog
     * @description 操作执行之前调用，封装日志数据
     * @author quchenxi
     * @date 2024/1/22 16:01
     * @param sysLog
     * @param joinPoint
     * @param sysOperLog
     * @return void
     */
    public static void beforeHandleLog(Log sysLog,
                                       ProceedingJoinPoint joinPoint,
                                       SysOperLog sysOperLog) {

        // 设置操作模块名称
        sysOperLog.setTitle(sysLog.title());
        sysOperLog.setOperatorType(sysLog.operatorType().name());

        // 获取目标方法信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature() ;
        Method method = methodSignature.getMethod();
        sysOperLog.setMethod(method.getDeclaringClass().getName());

        // 获取请求相关参数
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        sysOperLog.setRequestMethod(request.getMethod());
        sysOperLog.setOperUrl(request.getRequestURI());
        sysOperLog.setOperIp(request.getRemoteAddr());

        // 设置请求参数
        if(sysLog.isSaveRequestData()) {
            String requestMethod = sysOperLog.getRequestMethod();
            if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
                String params = Arrays.toString(joinPoint.getArgs());
                sysOperLog.setOperParam(params);
            }
        }

        // 获取用户名，测试阶段注释掉
        //        sysOperLog.setOperName(AuthContextUtil.get().getUserName());
    }
}
