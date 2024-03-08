package com.qcc.spzx.product.controller;

import com.qcc.spzx.model.entity.product.Category;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: CategoryController
 * @Description: 分类接口
 * @Date 2024/1/24 18:22
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "分类接口")
@RestController
@RequestMapping("/api/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @title findCategoryTree
     * @description 查询所有分类，树形封装
     * @author quchenxi
     * @date 2024/1/24 18:25
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "查询所有分类，树形封装")
    @GetMapping("/findCategoryTree")
    public Result findCategoryTree() {
        List<Category> list = categoryService.findCategoryTree();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
