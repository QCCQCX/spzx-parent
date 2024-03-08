package com.qcc.spzx.common.log.aspect;

import com.qcc.spzx.common.log.annotation.Log;
import com.qcc.spzx.common.log.service.AsyncOperLogService;
import com.qcc.spzx.common.log.utils.LogUtil;
import com.qcc.spzx.model.entity.system.SysOperLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: LogAspect
 * @Description: 日志切面类
 * @Date 2024/1/21 14:16
 * @Author quchenxi
 * @Version 1.0
 */
@Aspect
@Component
public class LogAspect {

    // 注入AsyncOperLogService，以调用mapper方法操作数据库，service实现类以及mapper都在manager中
    @Autowired
    private AsyncOperLogService asyncOperLogService;

    @Around("@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Log sysLog) {
        // 测试环绕通知
//        String title = sysLog.title();
//        int businessType = sysLog.businessType();
//        System.out.println( "环绕通知输出1：" + title + "   " + businessType);


        // 新建日志信息对象
        SysOperLog sysOperLog = new SysOperLog();
        // 封装sysOperLog数据
        LogUtil.beforeHandleLog(sysLog, joinPoint, sysOperLog);

        Object proceed = null;
        try {
            proceed = joinPoint.proceed();

            // 测试环绕通知
//            System.out.println("环绕通知输出2：");

            // 继续封装业务方法执行后产生的数据
            LogUtil.afterHandlLog(sysLog, proceed, sysOperLog, 0, null);

        } catch (Throwable e) {
            e.printStackTrace();

            // 若出现异常，状态码置1，将错误信息传入封装
            LogUtil.afterHandlLog(sysLog, proceed, sysOperLog, 1, e.getMessage());

            // 这里一定要抛出异常！！！（也可以增高日志切面的优先级）
            // 由于方法在执行事务时，事务的切面优先级高于日志切面优先级，包裹日志切面
            // 当执行方法出错后，在这里捕获到异常需要抛到外层的事务切面中，否则事务切面无法捕获到异常，导致事务无法回滚！
            throw new RuntimeException();
        }

        // 将封装好数据的sysOperLog保存到数据库
        asyncOperLogService.saveSysOperLog(sysOperLog);
        return proceed;
    }
}
