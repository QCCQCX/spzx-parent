package com.qcc.spzx.product.mapper;

import com.qcc.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: ProductDetailsMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/2/1 13:54
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface ProductDetailsMapper {
    /**
     * @title getByProductId
     * @description 根据productId获取商品详情信息
     * @author quchenxi
     * @date 2024/2/1 13:58
     * @param productId
     * @return com.qcc.spzx.model.entity.product.ProductDetails
     */
    ProductDetails getByProductId(Long productId);
}
