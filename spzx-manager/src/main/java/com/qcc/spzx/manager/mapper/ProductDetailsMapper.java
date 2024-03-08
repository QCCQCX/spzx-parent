package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: ProductDetailsMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/18 13:12
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface ProductDetailsMapper {
    /**
     * @title save
     * @description 详情添加
     * @author quchenxi
     * @date 2024/1/18 13:15
     * @param
     * @return void
     */
    void save();

    /**
     * @title findProductDetailsByProductId
     * @description 根据商品id查询商品详情数据
     * @author quchenxi
     * @date 2024/1/18 15:44
     * @param id
     * @return com.qcc.spzx.model.entity.product.ProductDetails
     */
    ProductDetails findProductDetailsByProductId(Long id);

    /**
     * @title updateById
     * @description 根据商品id修改商品详情数据
     * @author quchenxi
     * @date 2024/1/19 14:12
     * @param productDetails
     * @return void
     */
    void updateById(ProductDetails productDetails);

    /**
     * @title deleteByProductId
     * @description 根据商品id删除商品详情数据
     * @author quchenxi
     * @date 2024/1/19 15:08
     * @param productId
     * @return void
     */
    void deleteByProductId(Long productId);
}
