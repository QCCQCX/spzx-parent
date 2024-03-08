package com.qcc.spzx.user;

import com.qcc.spzx.common.anno.EnableUserLoginAuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: UserService
 * @Description: 此处输入类描述信息
 * @Date 2024/2/1 14:55
 * @Author quchenxi
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.qcc.spzx"}) // SpringBoot默认组件扫描范围为启动类所在包及其子包，添加自定义规则使得Knife4jConfig配置类生效
@EnableUserLoginAuthInterceptor // 引入common-service中的拦截器等相关配置
public class UserService {
    public static void main(String[] args) {
        SpringApplication.run(UserService.class, args);
    }
}
