package com.qcc.spzx.common.anno;

import com.qcc.spzx.common.config.UserWebMvcConfiguration;
import com.qcc.spzx.common.interceptor.UserLoginAuthInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: EnableUserLoginAuthInterceptor
 * @Description: 自定义注解，用于将拦截器引入service-user
 * @Date 2024/2/2 17:28
 * @Author quchenxi
 * @Version 1.0
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = {UserLoginAuthInterceptor.class, UserWebMvcConfiguration.class})
public @interface EnableUserLoginAuthInterceptor {
}
