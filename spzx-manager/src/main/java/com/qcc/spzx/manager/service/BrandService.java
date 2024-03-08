package com.qcc.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.model.entity.product.Brand;

import java.util.List;

/**
 * @ClassName: BrandService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/16 19:46
 * @Author quchenxi
 * @Version 1.0
 */
public interface BrandService {
    /**
     * @title findByPage
     * @description 品牌列表
     * @author quchenxi
     * @date 2024/1/16 19:53
     * @param page
     * @param limit
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.product.Brand>
     */
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    /**
     * @title save
     * @description 品牌添加
     * @author quchenxi
     * @date 2024/1/16 20:04
     * @param brand
     * @return void
     */
    void save(Brand brand);

    /**
     * @title updateById
     * @description 品牌修改
     * @author quchenxi
     * @date 2024/1/16 20:13
     * @param brand
     * @return void
     */
    void updateById(Brand brand);

    /**
     * @title deleteById
     * @description 品牌删除
     * @author quchenxi
     * @date 2024/1/16 20:27
     * @param id
     * @return void
     */
    void deleteById(Long id);

    /**
     * @title findAll
     * @description 查询所有品牌
     * @author quchenxi
     * @date 2024/1/16 20:52
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Brand>
     */
    List<Brand> findAll();
}
