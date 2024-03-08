package com.qcc.spzx.product.service;

import com.qcc.spzx.model.entity.product.Category;

import java.util.List;

/**
 * @ClassName: CategoryService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/23 15:13
 * @Author quchenxi
 * @Version 1.0
 */
public interface CategoryService {
    /**
     * @title selectOneCategory
     * @description 查询所有一级分类
     * @author quchenxi
     * @date 2024/1/23 15:27
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Category>
     */
    List<Category> selectOneCategory();

    /**
     * @title findCategoryTree
     * @description 查询所有分类，树形封装
     * @author quchenxi
     * @date 2024/1/24 18:27
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Category>
     */
    List<Category> findCategoryTree();
}
