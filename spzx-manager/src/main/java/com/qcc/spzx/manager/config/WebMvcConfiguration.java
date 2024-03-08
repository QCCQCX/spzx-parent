package com.qcc.spzx.manager.config;

import com.qcc.spzx.manager.interceptor.LoginAuthInterceptor;
import com.qcc.spzx.manager.properties.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: WebMvcConfiguration
 * @Description: 自定义配置类
 * @Date 2024/1/10 23:54
 * @Author quchenxi
 * @Version 1.0
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor;

    @Autowired
    private UserProperties userProperties;

    /**
     * @title addInterceptors
     * @description 拦截器注册
     * @author quchenxi
     * @date 2024/1/10 23:56
     * @param registry
     * @return void
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginAuthInterceptor)
////                .excludePathPatterns("/admin/system/index/login",
////                        "/admin/system/index/generateValidateCode")
//                .addPathPatterns("/**")
//                .excludePathPatterns(userProperties.getNoAuthUrls());
    }

    /**
     * @title addCorsMappings
     * @description 跨域
     * @author quchenxi
     * @date 2024/1/10 23:54
     * @param registry
     * @return void
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")      // 添加路径规则
                .allowCredentials(true)               // 是否允许在跨域的情况下传递Cookie
                .allowedOriginPatterns("*")           // 允许请求来源的域规则
                .allowedMethods("*")
                .allowedHeaders("*") ;                // 允许所有的请求头
    }
}
