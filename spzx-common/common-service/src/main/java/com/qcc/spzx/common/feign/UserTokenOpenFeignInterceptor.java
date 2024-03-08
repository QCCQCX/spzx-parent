package com.qcc.spzx.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ClassName: UserTokenOpenFeignInterceptor
 * @Description: 远程调用拦截器
 * @Date 2024/2/5 17:14
 * @Author quchenxi
 * @Version 1.0
 */
public class UserTokenOpenFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 获取request对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 远程调用前，把token从原请求中取出来，设置到远程调用请求头中
        String token = request.getHeader("token");
        requestTemplate.header("token", token);
    }
}
