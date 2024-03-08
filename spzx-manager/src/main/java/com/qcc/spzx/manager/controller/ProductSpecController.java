package com.qcc.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.manager.service.ProductSpecService;
import com.qcc.spzx.model.entity.product.ProductSpec;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: ProductSpecController
 * @Description: 商品规格表现层
 * @Date 2024/1/17 17:09
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "商品规格接口")
@RestController
@RequestMapping("/admin/product/productSpec")
public class ProductSpecController {

    @Autowired
    private ProductSpecService productSpecService;

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title list
     * @description 商品规格列表
     * @author quchenxi
     * @date 2024/1/17 17:16
     */
    @Operation(summary = "商品规格列表")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable("page") Integer page,
                       @PathVariable("limit") Integer limit) {
        PageInfo<ProductSpec> pageInfo = productSpecService.findByPage(page, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title save
     * @description 商品规格添加
     * @author quchenxi
     * @date 2024/1/17 18:16
     * @param productSpec
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "商品规格添加")
    @PostMapping("/save")
    public Result save(@RequestBody ProductSpec productSpec) {
        productSpecService.save(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title updateById
     * @description 商品规格修改
     * @author quchenxi
     * @date 2024/1/17 18:18
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "商品规格修改")
    @PutMapping("/updateById")
    public Result updateById(@RequestBody ProductSpec productSpec) {
        productSpecService.updateById(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title deleteById
     * @description 商品规格删除
     * @author quchenxi
     * @date 2024/1/17 18:27
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "商品规格删除")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Long id) {
        productSpecService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title findAll
     * @description 查询所有商品规格
     * @author quchenxi
     * @date 2024/1/18 11:34
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "查询所有商品规格")
    @GetMapping("/findAll")
    public Result findAll() {
        List<ProductSpec> list = productSpecService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
