package com.qcc.spzx.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @ClassName: ProductApplication
 * @Description: 此处输入类描述信息
 * @Date 2024/1/23 15:04
 * @Author quchenxi
 * @Version 1.0
 */
@SpringBootApplication
@EnableCaching // 开启缓存功能
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
