package com.qcc.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.model.dto.product.CategoryBrandDto;
import com.qcc.spzx.model.entity.product.Brand;
import com.qcc.spzx.model.entity.product.CategoryBrand;

import java.util.List;

/**
 * @ClassName: CategoryBrandService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/16 21:01
 * @Author quchenxi
 * @Version 1.0
 */
public interface CategoryBrandService {
    /**
     * @title findByPage
     * @description 分类品牌条件分页查询
     * @author quchenxi
     * @date 2024/1/16 21:11
     * @param page
     * @param limit
     * @param categoryBrandDto
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.product.CategoryBrand>
     */
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    /**
     * @title save
     * @description 分类品牌添加
     * @author quchenxi
     * @date 2024/1/17 10:43
     * @param categoryBrand
     * @return void
     */
    void save(CategoryBrand categoryBrand);

    /**
     * @title updateById
     * @description 分类品牌修改
     * @author quchenxi
     * @date 2024/1/17 10:57
     * @param categoryBrand
     * @return void
     */
    void updateById(CategoryBrand categoryBrand);

    /**
     * @title deleteById
     * @description 分类品牌删除
     * @author quchenxi
     * @date 2024/1/17 11:04
     * @param id
     * @return void
     */
    void deleteById(Long id);

    /**
     * @title findBrandByCategoryId
     * @description 根据分类id查询品牌
     * @author quchenxi
     * @date 2024/1/18 10:53
     * @param categoryId
     * @return java.util.List<com.qcc.spzx.model.entity.product.Brand>
     */
    List<Brand> findBrandByCategoryId(Long categoryId);
}
