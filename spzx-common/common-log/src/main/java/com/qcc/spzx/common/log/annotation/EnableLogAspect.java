package com.qcc.spzx.common.log.annotation;

import com.qcc.spzx.common.log.aspect.LogAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: EnableLogAspect
 * @Description: 开启日志功能注解
 * @Date 2024/1/21 15:04
 * @Author quchenxi
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(LogAspect.class) // 将切面类导入，供spring扫描
public @interface EnableLogAspect {
}
