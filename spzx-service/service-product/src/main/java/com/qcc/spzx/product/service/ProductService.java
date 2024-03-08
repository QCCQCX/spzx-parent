package com.qcc.spzx.product.service;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.model.dto.h5.ProductSkuDto;
import com.qcc.spzx.model.entity.product.ProductSku;
import com.qcc.spzx.model.vo.h5.ProductItemVo;

import java.util.List;

/**
 * @ClassName: ProductService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/23 15:12
 * @Author quchenxi
 * @Version 1.0
 */
public interface ProductService {
    /**
     * @title selectProductSkuBySale
     * @description 获取销量排序前十的商品
     * @author quchenxi
     * @date 2024/1/23 15:36
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.ProductSku>
     */
    List<ProductSku> selectProductSkuBySale();

    /**
     * @title findByPage
     * @description 商品列表
     * @author quchenxi
     * @date 2024/2/1 10:37
     * @param page
     * @param limit
     * @param productSkuDto
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.product.ProductSku>
     */
    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    /**
     * @title item
     * @description 商品详情
     * @author quchenxi
     * @date 2024/2/1 13:38
     * @param skuId
     * @return com.qcc.spzx.model.vo.h5.ProductItemVo
     */
    ProductItemVo item(Long skuId);

    /**
     * @title getBySkuId
     * @description 根据skuId查询sku信息
     * @author quchenxi
     * @date 2024/2/3 13:44
     * @param skuId
     * @return com.qcc.spzx.model.entity.product.ProductSku
     */
    ProductSku getBySkuId(Long skuId);
}
