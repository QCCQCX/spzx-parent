package com.qcc.spzx.manager.controller;

import com.qcc.spzx.manager.service.CategoryService;
import com.qcc.spzx.model.entity.product.Brand;
import com.qcc.spzx.model.entity.product.Category;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName: CategoryController
 * @Description: 商品分类表现层
 * @Date 2024/1/14 14:52
 * @Author quchenxi
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/system/category")
@Tag(name = "商品分类接口")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * @title findCategoryList
     * @description 分类列表（每次只查询一层数据，即懒查询）
     * @author quchenxi
     * @date 2024/1/14 15:24
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "分类列表")
    @GetMapping("/findCategoryList/{id}")
    public Result findCategoryList(@PathVariable("id") Long id) {
        List<Category> list = categoryService.findCategoryList(id);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title exportData
     * @description 导出分类
     * @author quchenxi
     * @date 2024/1/16 15:01
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "导出分类")
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }

    /**
     * @title importData
     * @description 导入分类
     * @author quchenxi
     * @date 2024/1/16 16:56
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "导入分类")
    @PostMapping("/importData")
    public Result importData(MultipartFile file) {
        categoryService.importData(file);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
