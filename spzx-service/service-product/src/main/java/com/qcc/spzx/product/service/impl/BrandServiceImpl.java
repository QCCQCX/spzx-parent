package com.qcc.spzx.product.service.impl;

import com.qcc.spzx.model.entity.product.Brand;
import com.qcc.spzx.product.mapper.BrandMapper;
import com.qcc.spzx.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: BrandServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/28 20:50
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    /**
     * @title findAll
     * @description 获取所有品牌
     * @author quchenxi
     * @date 2024/1/28 20:55
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Brand>
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}
