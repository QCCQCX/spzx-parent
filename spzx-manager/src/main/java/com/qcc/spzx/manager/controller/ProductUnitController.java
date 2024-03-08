package com.qcc.spzx.manager.controller;

import com.qcc.spzx.manager.service.ProductUnitService;
import com.qcc.spzx.model.entity.base.ProductUnit;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: ProductUnitController
 * @Description: 商品计量单位接口
 * @Date 2024/1/18 11:04
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "商品计量单位接口")
@RestController
@RequestMapping("/admin/product/productUnit")
public class ProductUnitController {

    @Autowired
    private ProductUnitService productUnitService;

    /**
     * @title findAll
     * @description 查询所有商品计量单位
     * @author quchenxi
     * @date 2024/1/18 11:12
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "查询所有商品计量单位")
    @GetMapping("/findAll")
    public Result findAll() {
        List<ProductUnit> list = productUnitService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }
}
