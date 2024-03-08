package com.qcc.spzx.order;

import com.qcc.spzx.common.anno.EnableUserLoginAuthInterceptor;
import com.qcc.spzx.common.anno.EnableUserTokenFeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: OrderApplication
 * @Description: 此处输入类描述信息
 * @Date 2024/2/4 22:26
 * @Author quchenxi
 * @Version 1.0
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.qcc.spzx"}) // 开启远程调用功能
@EnableUserTokenFeignInterceptor // 自定义注解，引入远程调用拦截器类
@EnableUserLoginAuthInterceptor // 自定义注解，引入threadLocal拦截器
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
