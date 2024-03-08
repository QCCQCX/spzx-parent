package com.qcc.spzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.qcc.spzx.model.entity.product.Category;
import com.qcc.spzx.product.mapper.CategoryMapper;
import com.qcc.spzx.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName: CategoryServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/23 15:14
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * @title selectOneCategory
     * @description 查询所有一级分类（手动缓存处理）
     * @author quchenxi
     * @date 2024/1/23 15:27
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Category>
     */
    @Override
    public List<Category> selectOneCategory() {
        // 查询redis中是否有所有一级分类数据（key自定义）
        String categoryOneJson = redisTemplate.opsForValue().get("category:one");

        // 若有，则返回
        if (StringUtils.hasText(categoryOneJson)) {
            // 将json格式字符串转换成list集合
            List<Category> ExistCategoryList = JSON.parseArray(categoryOneJson, Category.class);
            return ExistCategoryList;
        }

        // 若没有，查询数据库，把结果放到redis中后返回
        List<Category> categoryList = categoryMapper.selectOneCategory();
        // 将list集合转换成json字符串，存入redis，7天有效
        redisTemplate.opsForValue().set("category:one", JSON.toJSONString(categoryList), 7, TimeUnit.DAYS);

        return categoryList;
    }

    /**
     * @title findCategoryTree
     * @description 查询所有分类，树形封装（注解实现缓存）
     * @author quchenxi
     * @date 2024/1/24 18:27
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Category>
     */
    @Cacheable(value = "category", key = "'all'") // 设置存入redis的key，规则：value值::key值，key值设置需要添加单引号
    @Override
    public List<Category> findCategoryTree() {

        // 查询所有分类，返回list集合
        List<Category> allCategoryList = categoryMapper.findAll();

        // 遍历所有分类的list集合，得到所有一级分类
        List<Category> oneCategoryList = allCategoryList.stream()
                .filter(item -> item.getParentId().longValue() == 0)
                .collect(Collectors.toList());

        // 遍历所有一级分类的list集合，得到所有二级分类
        oneCategoryList.forEach(oneCategory -> {
            List<Category> twoCategoryList = allCategoryList.stream()
                    .filter(item -> item.getParentId().equals(oneCategory.getId()))
                    .collect(Collectors.toList());
            // 封装到每一个一级分类中
            oneCategory.setChildren(twoCategoryList);

            // 遍历所有二级分类的list集合，得到所有三级分类
            twoCategoryList.forEach(twoCategory -> {
                List<Category> threeCategoryList = allCategoryList.stream()
                        .filter(item -> twoCategory.getId().equals(item.getParentId()))
                        .collect(Collectors.toList());
                // 封装到每一个二级分类中
                twoCategory.setChildren(threeCategoryList);
            });
        });
        return oneCategoryList;
    }
}
