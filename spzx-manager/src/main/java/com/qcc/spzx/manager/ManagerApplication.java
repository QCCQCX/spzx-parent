package com.qcc.spzx.manager;

import com.qcc.spzx.common.log.annotation.EnableLogAspect;
import com.qcc.spzx.manager.properties.MinioProperties;
import com.qcc.spzx.manager.properties.UserProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ManagerApplication
 * @Description: 此处输入类描述信息
 * @Date 2024/1/10 18:44
 * @Author quchenxi
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan("com.qcc.spzx") // SpringBoot默认组件扫描范围为启动类所在包及其子包，添加自定义规则使得Knife4jConfig配置类生效
@EnableConfigurationProperties(value = {UserProperties.class, MinioProperties.class}) // 添加自定义属性类
@EnableScheduling // 开启定时执行功能
@EnableLogAspect // 开启日志功能（自定义注解）
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
