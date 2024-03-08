package com.qcc.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qcc.spzx.manager.mapper.ProductSpecMapper;
import com.qcc.spzx.manager.service.ProductSpecService;
import com.qcc.spzx.model.entity.product.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ProductSpecServiceImpl
 * @Description: 商品规格业务层
 * @Date 2024/1/17 17:12
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class ProductSpecServiceImpl implements ProductSpecService {

    @Autowired
    private ProductSpecMapper productSpecMapper;

    /**
     * @title findByPage
     * @description 商品规格列表
     * @author quchenxi
     * @date 2024/1/17 18:28
     * @param page
     * @param limit
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.product.ProductSpec>
     */
    @Override
    public PageInfo<ProductSpec> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<ProductSpec> list = productSpecMapper.findByPage();
        PageInfo<ProductSpec> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * @title save
     * @description 商品规格添加
     * @author quchenxi
     * @date 2024/1/17 18:28
     * @param productSpec
     * @return void
     */
    @Override
    public void save(ProductSpec productSpec) {
        productSpecMapper.save(productSpec);
    }

    /**
     * @title updateById
     * @description 商品规格修改
     * @author quchenxi
     * @date 2024/1/17 18:29
     * @param productSpec
     * @return void
     */
    @Override
    public void updateById(ProductSpec productSpec) {
        productSpecMapper.update(productSpec);
    }

    /**
     * @title deleteById
     * @description 商品规格删除
     * @author quchenxi
     * @date 2024/1/17 18:29
     * @param id
     * @return void
     */
    @Override
    public void deleteById(Long id) {
        productSpecMapper.delete(id);
    }

    /**
     * @title findAll
     * @description 查询所有商品规格
     * @author quchenxi
     * @date 2024/1/18 11:35
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.ProductSpec>
     */
    @Override
    public List<ProductSpec> findAll() {
        return productSpecMapper.findAll();
    }
}
