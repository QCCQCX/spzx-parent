package com.qcc.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.manager.service.BrandService;
import com.qcc.spzx.model.entity.product.Brand;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: BrandController
 * @Description: 品牌表现层
 * @Date 2024/1/16 19:47
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "品牌接口")
@RestController
@RequestMapping("/admin/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title list
     * @description 品牌列表
     * @author quchenxi
     * @date 2024/1/16 19:51
     */
    @Operation(summary = "品牌列表")
    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable("page") Integer page,
                       @PathVariable("limit") Integer limit) {
        PageInfo<Brand> brandPageInfo = brandService.findByPage(page, limit);
        return Result.build(brandPageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title save
     * @description 品牌添加
     * @author quchenxi
     * @date 2024/1/16 20:02
     */
    @Operation(summary = "品牌添加")
    @PostMapping("/save")
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title updateById
     * @description 品牌修改
     * @author quchenxi
     * @date 2024/1/16 20:12
     */
    @Operation(summary = "品牌修改")
    @PostMapping("/updateById")
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title deleteById
     * @description 品牌删除
     * @author quchenxi
     * @date 2024/1/16 20:26
     */
    @Operation(summary = "品牌删除")
    @GetMapping("/deletedById/{id}")
    public Result deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title findAll
     * @description 查询所有品牌
     * @author quchenxi
     * @date 2024/1/16 20:51
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "查询所有品牌")
    @GetMapping("/findAll")
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
