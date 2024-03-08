package com.qcc.spzx.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: Knife4jConfig
 * @Description: 此处输入类描述信息
 * @Date 2024/1/10 15:53
 * @Author quchenxi
 * @Version 1.0
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public GroupedOpenApi adminApi() {      // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("admin-api")         // 接口分组名称
                .pathsToMatch("/admin/**")  // 接口请求路径规则
                .build();
    }

    @Bean
    public GroupedOpenApi userApi() {      // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("user-api")         // 接口分组名称
                .pathsToMatch("/api/**")  // 接口请求路径规则
                .build();
    }

    /**
     * @title customOpenAPI
     * @description 自定义接口信息
     * @author quchenxi
     * @date 2024/1/10 15:56
     * @param
     * @return io.swagger.v3.oas.models.OpenAPI
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("尚品甄选API接口文档")
                        .version("1.0")
                        .description("尚品甄选API接口文档")
                        .contact(new Contact().name("qcc"))); // 设定作者
    }
}
