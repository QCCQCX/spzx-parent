package com.qcc.spzx.manager.service.impl;

import com.qcc.spzx.manager.mapper.ProductUnitMapper;
import com.qcc.spzx.manager.service.ProductUnitService;
import com.qcc.spzx.model.entity.base.ProductUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ProductUnitServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/18 11:03
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    @Autowired
    private ProductUnitMapper productUnitMapper;

    /**
     * @title findAll
     * @description 查询所有商品计量单位
     * @author quchenxi
     * @date 2024/1/18 11:14
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.base.ProductUnit>
     */
    @Override
    public List<ProductUnit> findAll() {
        return productUnitMapper.findAll();
    }
}
