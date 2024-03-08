package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.dto.product.CategoryBrandDto;
import com.qcc.spzx.model.entity.product.Brand;
import com.qcc.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: CategoryBrandMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/16 21:01
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface CategoryBrandMapper {
    /**
     * @title findByPage
     * @description 分类品牌条件分页查询
     * @author quchenxi
     * @date 2024/1/16 21:15
     * @param categoryBrandDto
     * @return java.util.List<com.qcc.spzx.model.entity.product.CategoryBrand>
     */
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);

    /**
     * @title save
     * @description 分类品牌添加
     * @author quchenxi
     * @date 2024/1/17 10:44
     * @param categoryBrand
     * @return void
     */
    void save(CategoryBrand categoryBrand);

    /**
     * @title updateById
     * @description 分类品牌修改
     * @author quchenxi
     * @date 2024/1/17 10:58
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
     * @date 2024/1/18 10:56
     * @param categoryId
     * @return java.util.List<com.qcc.spzx.model.entity.product.Brand>
     */
    List<Brand> findBrandByCategoryId(Long categoryId);
}
