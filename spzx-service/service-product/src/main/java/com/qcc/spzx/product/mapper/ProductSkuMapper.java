package com.qcc.spzx.product.mapper;

import com.qcc.spzx.model.dto.h5.ProductSkuDto;
import com.qcc.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: ProductSkuMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/23 15:15
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface ProductSkuMapper {
    /**
     * @title selectProductSkuBySale
     * @description 获取销量排序前十的商品
     * @author quchenxi
     * @date 2024/1/23 15:38
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.ProductSku>
     */
    List<ProductSku> selectProductSkuBySale();

    /**
     * @title findByPage
     * @description 商品列表
     * @author quchenxi
     * @date 2024/2/1 10:40
     * @param productSkuDto
     * @return java.util.List<com.qcc.spzx.model.entity.product.ProductSku>
     */
    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    /**
     * @title getById
     * @description 根据id获取sku
     * @author quchenxi
     * @date 2024/2/1 13:42
     * @param skuId
     * @return com.qcc.spzx.model.entity.product.ProductSku
     */
    ProductSku getById(Long skuId);

    /**
     * @title findByProductId
     * @description 根据productId获取所有sku
     * @author quchenxi
     * @date 2024/2/1 14:11
     * @param productId
     * @return java.util.List<com.qcc.spzx.model.entity.product.ProductSku>
     */
    List<ProductSku> findByProductId(Long productId);
}
