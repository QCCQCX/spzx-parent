package com.qcc.spzx.feign.product;

import com.qcc.spzx.model.entity.product.ProductSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: ProductFeignClient
 * @Description: 使用openFeign进行远程调用
 * @Date 2024/2/3 13:51
 * @Author quchenxi
 * @Version 1.0
 */
@FeignClient("service-product") // value值为需要远程调用的服务名称
public interface ProductFeignClient {

    /**
     * @title getBySkuId
     * @description 需要远程调用的方法签名（service-product中的controller层中的方法）
     * @author quchenxi
     * @date 2024/2/3 13:56
     * @param skuId
     * @return com.qcc.spzx.model.entity.product.ProductSku
     */
    @GetMapping("/api/product/getBySkuId/{skuId}") // 此处的映射路径需要全路径
    public ProductSku getBySkuId(@PathVariable("skuId") Long skuId);
}
