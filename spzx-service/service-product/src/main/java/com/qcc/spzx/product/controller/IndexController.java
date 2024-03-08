package com.qcc.spzx.product.controller;

import com.qcc.spzx.model.entity.product.Category;
import com.qcc.spzx.model.entity.product.Product;
import com.qcc.spzx.model.entity.product.ProductSku;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.model.vo.h5.IndexVo;
import com.qcc.spzx.product.service.CategoryService;
import com.qcc.spzx.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: IndexController
 * @Description: 首页接口
 * @Date 2024/1/23 15:09
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "首页接口")
@RestController
@RequestMapping("/api/product/index")
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "首页接口")
    @GetMapping
    public Result index() {
        // 查询所有一级分类
        List<Category> categoryList = categoryService.selectOneCategory();

        // 根据销量排序，获取前10条数据
        List<ProductSku> productSkuList = productService.selectProductSkuBySale();

        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return Result.build(indexVo, ResultCodeEnum.SUCCESS);
    }
}
