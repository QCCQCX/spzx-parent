package com.qcc.spzx.product.mapper;

import com.qcc.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: ProductMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/2/1 13:47
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface ProductMapper {
    /**
     * @title getById
     * @description 根据id获取商品信息
     * @author quchenxi
     * @date 2024/2/1 13:48
     * @param productId
     * @return com.qcc.spzx.model.entity.product.Product
     */
    Product getById(Long productId);
}
