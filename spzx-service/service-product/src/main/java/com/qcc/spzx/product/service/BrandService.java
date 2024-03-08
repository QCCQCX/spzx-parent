package com.qcc.spzx.product.service;

import com.qcc.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * @ClassName: BrandService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/28 20:49
 * @Author quchenxi
 * @Version 1.0
 */
public interface BrandService {
    /**
     * @title findAll
     * @description 获取所有品牌
     * @author quchenxi
     * @date 2024/1/28 20:55
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Brand>
     */
    List<Brand> findAll();
}
