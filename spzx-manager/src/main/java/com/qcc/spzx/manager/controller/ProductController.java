package com.qcc.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.google.j2objc.annotations.AutoreleasePool;
import com.qcc.spzx.manager.service.ProductService;
import com.qcc.spzx.model.dto.product.ProductDto;
import com.qcc.spzx.model.entity.product.Product;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ProductController
 * @Description: 商品接口
 * @Date 2024/1/17 19:06
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "商品接口")
@RestController
@RequestMapping("/admin/product/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * @title list
     * @description 商品条件分页查询
     * @author quchenxi
     * @date 2024/1/17 20:20
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "商品条件分页查询")
    @PostMapping("/{page}/{limit}")
    public Result list(@PathVariable("page") Integer page,
                       @PathVariable("limit") Integer limit,
                       @RequestBody ProductDto productDto) {
        PageInfo<Product> pageInfo = productService.findByPage(page, limit, productDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title save
     * @description 商品添加
     * @author quchenxi
     * @date 2024/1/18 12:47
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "商品添加")
    @PostMapping("/save")
    public Result save(@RequestBody Product product) {
        productService.save(product);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title getById
     * @description 根据id查询商品
     * @author quchenxi
     * @date 2024/1/18 15:33
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "根据id查询商品")
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return Result.build(product, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title update
     * @description 商品修改
     * @author quchenxi
     * @date 2024/1/19 13:34
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "商品修改")
    @PutMapping("/updateById")
    public Result update(@RequestBody Product product) {
        productService.update(product);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title deleteById
     * @description 商品修改
     * @author quchenxi
     * @date 2024/1/19 14:47
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "商品删除")
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @title updateAuditStatus
     * @description 商品审核状态修改
     * @author quchenxi
     * @date 2024/1/19 15:23
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "商品审核状态修改")
    @GetMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable("id") Long id,
                                    @PathVariable("auditStatus") Integer auditStatus) {
        productService.updateAuditStatus(id, auditStatus);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     * @title updateStatus
     * @description 商品状态修改
     * @author quchenxi
     * @date 2024/1/19 15:31
     */
    @Operation(summary = "商品状态修改")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable("id") Long id,
                               @PathVariable("status") Integer status) {
        productService.updateStatus(id, status);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
