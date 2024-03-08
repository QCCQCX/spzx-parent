package com.qcc.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qcc.spzx.manager.mapper.CategoryBrandMapper;
import com.qcc.spzx.manager.service.CategoryBrandService;
import com.qcc.spzx.model.dto.product.CategoryBrandDto;
import com.qcc.spzx.model.entity.product.Brand;
import com.qcc.spzx.model.entity.product.Category;
import com.qcc.spzx.model.entity.product.CategoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: CategoryBrandServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/16 21:01
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    /**
     * @param page
     * @param limit
     * @param categoryBrandDto
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.product.CategoryBrand>
     * @title findByPage
     * @description 分类品牌条件分页查询
     * @author quchenxi
     * @date 2024/1/16 21:11
     */
    @Override
    public PageInfo<CategoryBrand> findByPage(Integer page,
                                              Integer limit,
                                              CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(page, limit);
        List<CategoryBrand> list = categoryBrandMapper.findByPage(categoryBrandDto);
        PageInfo<CategoryBrand> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * @title save
     * @description 分类品牌添加
     * @author quchenxi
     * @date 2024/1/17 10:43
     * @param categoryBrand
     * @return void
     */
    @Override
    public void save(CategoryBrand categoryBrand) {
        categoryBrandMapper.save(categoryBrand);
    }

    /**
     * @title updateById
     * @description 分类品牌修改
     * @author quchenxi
     * @date 2024/1/17 10:57
     * @param categoryBrand
     * @return void
     */
    @Override
    public void updateById(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand);
    }

    /**
     * @title deleteById
     * @description 分类品牌删除
     * @author quchenxi
     * @date 2024/1/17 11:04
     * @param id
     * @return void
     */
    @Override
    public void deleteById(Long id) {
        categoryBrandMapper.deleteById(id);
    }

    /**
     * @title findBrandByCategoryId
     * @description 根据分类id查询品牌
     * @author quchenxi
     * @date 2024/1/18 10:54
     * @param categoryId
     * @return java.util.List<com.qcc.spzx.model.entity.product.Brand>
     */
    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {
        return categoryBrandMapper.findBrandByCategoryId(categoryId);
    }
}
