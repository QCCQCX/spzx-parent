package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: ProductSkuMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/18 13:08
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface ProductSkuMapper {
    /**
     * @title save
     * @description SKU添加
     * @author quchenxi
     * @date 2024/1/18 13:10
     * @param productSku
     * @return void
     */
    void save(ProductSku productSku);

    /**
     * @title findProductSkuByProductId
     * @description 根据商品id查询商品SKU列表
     * @author quchenxi
     * @date 2024/1/18 15:41
     * @param id
     * @return java.util.List<com.qcc.spzx.model.entity.product.ProductSku>
     */
    List<ProductSku> findProductSkuByProductId(Long id);

    /**
     * @title updateById
     * @description 根据商品id修改商品SKU列表
     * @author quchenxi
     * @date 2024/1/19 14:11
     * @param productSku
     * @return void
     */
    void updateById(ProductSku productSku);

    /**
     * @title deleteByProductId
     * @description 根据商品id删除商品SKU列表
     * @author quchenxi
     * @date 2024/1/19 15:02
     * @param productId
     * @return void
     */
    void deleteByProductId(Long productId);
}
