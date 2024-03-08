package com.qcc.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.model.entity.product.ProductSpec;

import java.util.List;

/**
 * @ClassName: ProductSpecService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/17 17:11
 * @Author quchenxi
 * @Version 1.0
 */
public interface ProductSpecService {
    /**
     * @title findByPage
     * @description 商品规格列表
     * @author quchenxi
     * @date 2024/1/17 18:28
     * @param page
     * @param limit
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.product.ProductSpec>
     */
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    /**
     * @title save
     * @description 商品规格添加
     * @author quchenxi
     * @date 2024/1/17 18:28
     * @param productSpec
     * @return void
     */
    void save(ProductSpec productSpec);

    /**
     * @title updateById
     * @description 商品规格修改
     * @author quchenxi
     * @date 2024/1/17 18:28
     * @param productSpec
     * @return void
     */
    void updateById(ProductSpec productSpec);

    /**
     * @title deleteById
     * @description 商品规格修改
     * @author quchenxi
     * @date 2024/1/17 18:29
     * @param id
     * @return void
     */
    void deleteById(Long id);

    /**
     * @title findAll
     * @description 查询所有商品规格
     * @author quchenxi
     * @date 2024/1/18 11:35
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.ProductSpec>
     */
    List<ProductSpec> findAll();
}
