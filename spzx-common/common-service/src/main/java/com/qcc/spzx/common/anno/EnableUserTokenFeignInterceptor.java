package com.qcc.spzx.common.anno;

import com.qcc.spzx.common.feign.UserTokenOpenFeignInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: EnableUserTokenFeignInterceptor
 * @Description: 自定义注解，用于导入远程调用拦截器
 * @Date 2024/2/5 17:19
 * @Author quchenxi
 * @Version 1.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = UserTokenOpenFeignInterceptor.class) // 导入远程调用拦截器
public @interface EnableUserTokenFeignInterceptor {
}
