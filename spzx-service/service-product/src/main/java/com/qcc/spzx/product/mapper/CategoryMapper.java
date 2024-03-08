package com.qcc.spzx.product.mapper;

import com.qcc.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: CategoryMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/23 15:14
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper {
    /**
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Category>
     * @title selectOneCategory
     * @description 查询所有一级分类
     * @author quchenxi
     * @date 2024/1/23 15:30
     */
    public List<Category> selectOneCategory();

    /**
     * @title findAll
     * @description 查询所有分类
     * @author quchenxi
     * @date 2024/1/24 19:40
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Category>
     */
    List<Category> findAll();
}
