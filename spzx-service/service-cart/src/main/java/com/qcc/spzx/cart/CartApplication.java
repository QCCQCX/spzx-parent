package com.qcc.spzx.cart;

import com.qcc.spzx.common.anno.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: CartApplication
 * @Description: 购物车服务，只需要操作redis，无需操作mysql，故应排除mybatis和mysql相关配置
 * @Date 2024/2/3 11:04
 * @Author quchenxi
 * @Version 1.0
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // exclude属性：用于排除数据源自动配置（排除spzx-service的pom中的mybatis和mysql的依赖项）
@EnableFeignClients(basePackages = {"com.qcc.spzx"}) // 开启远程调用功能
@EnableUserLoginAuthInterceptor // 引入自定义的拦截器，用于使用threadLocal操作用户信息
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}
