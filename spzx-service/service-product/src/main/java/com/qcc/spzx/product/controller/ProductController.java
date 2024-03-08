package com.qcc.spzx.product.controller;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.model.dto.h5.ProductSkuDto;
import com.qcc.spzx.model.entity.product.ProductSku;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.model.vo.h5.ProductItemVo;
import com.qcc.spzx.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ProductController
 * @Description: 商品接口
 * @Date 2024/2/1 10:29
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "商品接口")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * @title findByPage
     * @description 商品列表
     * @author quchenxi
     * @date 2024/2/1 10:37
     * @param page
     * @param limit
     * @param productSkuDto
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "商品列表")
    @GetMapping("/{page}/{limit}")
    public Result findByPage(@PathVariable Integer page,
                             @PathVariable Integer limit,
                             ProductSkuDto productSkuDto) {
        System.out.println("***************");
        System.out.println(productSkuDto);
        PageInfo<ProductSku> pageInfo = productService.findByPage(page, limit, productSkuDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title item
     * @description 商品详情
     * @author quchenxi
     * @date 2024/2/1 13:36
     */
    @Operation(summary = "商品详情")
    @GetMapping("/item/{skuId}")
    public Result item(@PathVariable Long skuId) {
        ProductItemVo productItemVo = productService.item(skuId);
        return Result.build(productItemVo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title getBySkuId
     * @description 根据skuId查询sku信息，供后端的远程调用使用
     * @author quchenxi
     * @date 2024/2/3 13:43
     * @param
     * @return com.qcc.spzx.model.entity.product.ProductSku
     */
    @Operation(summary = "根据skuId查询sku信息")
    @GetMapping("/getBySkuId/{skuId}")
    public ProductSku getBySkuId(@PathVariable("skuId") Long skuId) {
        ProductSku productSku = productService.getBySkuId(skuId);
        return productSku;
    }
}
