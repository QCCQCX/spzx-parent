package com.qcc.spzx.common.log.annotation;

import com.qcc.spzx.common.log.enums.OperatorType;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Log
 * @Description: 日志注解
 * @Date 2024/1/21 14:00
 * @Author quchenxi
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String title(); // 日志标题
    OperatorType operatorType() default OperatorType.MANAGE; // 操作员类型
    int businessType(); // 业务类型，0其他，1新增，2修改，3删除
    boolean isSaveRequestData() default true; // 是否保存请求数据
    boolean isSaveResponseData() default true; // 是否保存响应数据
}
