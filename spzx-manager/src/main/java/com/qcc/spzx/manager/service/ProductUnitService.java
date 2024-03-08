package com.qcc.spzx.manager.service;

import com.qcc.spzx.model.entity.base.ProductUnit;

import java.util.List;

/**
 * @ClassName: ProductUnitService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/18 11:03
 * @Author quchenxi
 * @Version 1.0
 */
public interface ProductUnitService {
    /**
     * @title findAll
     * @description 查询所有商品计量单位
     * @author quchenxi
     * @date 2024/1/18 11:14
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.base.ProductUnit>
     */
    List<ProductUnit> findAll();
}
