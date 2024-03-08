package com.qcc.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.manager.service.CategoryBrandService;
import com.qcc.spzx.model.dto.product.CategoryBrandDto;
import com.qcc.spzx.model.entity.product.Brand;
import com.qcc.spzx.model.entity.product.CategoryBrand;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: CategoryBrandController
 * @Description: 分类品牌表现层
 * @Date 2024/1/16 21:02
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "分类品牌接口")
@RestController
@RequestMapping("/admin/product/categoryBrand")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandService categoryBrandService;

    /**
     * @title findByPage
     * @description 分类品牌条件分页查询
     * @author quchenxi
     * @date 2024/1/16 21:08
     * @param page
     * @param limit
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "分类品牌条件分页查询")
    @PostMapping("/{page}/{limit}")
    public Result findByPage(@PathVariable("page") Integer page,
                             @PathVariable("limit") Integer limit,
                             @RequestBody CategoryBrandDto categoryBrandDto) {
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.findByPage(page, limit, categoryBrandDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title save
     * @description 分类品牌添加
     * @author quchenxi
     * @date 2024/1/17 10:41
     */
    @Operation(summary = "分类品牌添加")
    @PostMapping("/save")
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.save(categoryBrand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title updateById
     * @description 分类品牌修改
     * @author quchenxi
     * @date 2024/1/17 10:56
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "分类品牌修改")
    @PutMapping("/updateById")
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updateById(categoryBrand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title deleteById
     * @description 分类品牌删除
     * @author quchenxi
     * @date 2024/1/17 11:03
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "分类品牌删除")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        categoryBrandService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title findBrandByCategoryId
     * @description 根据分类id查询品牌
     * @author quchenxi
     * @date 2024/1/18 10:54
     * @param categoryId
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "根据分类id查询品牌")
    @GetMapping("/findBrandByCateoryId/{categoryId}")
    public Result findBrandByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<Brand> list = categoryBrandService.findBrandByCategoryId(categoryId);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
