package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.dto.product.ProductDto;
import com.qcc.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: ProductMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/17 19:04
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface ProductMapper {
    /**
     * @title findByPage
     * @description 商品条件分页查询
     * @author quchenxi
     * @date 2024/1/17 20:28
     * @param productDto
     * @return java.util.List<com.qcc.spzx.model.entity.product.Product>
     */
    List<Product> findByPage(ProductDto productDto);

    /**
     * @title save
     * @description 商品添加
     * @author quchenxi
     * @date 2024/1/18 12:48
     * @param product
     * @return void
     */
    void save(Product product);

    /**
     * @param
     * @param id
     * @return com.qcc.spzx.model.entity.product.Product
     * @title getProductById
     * @description 根据id查询商品
     * @author quchenxi
     * @date 2024/1/18 15:38
     */
    Product getProductById(Long id);

    /**
     * @title updateById
     * @description 修改商品基本信息
     * @author quchenxi
     * @date 2024/1/19 14:02
     * @param product
     * @return void
     */
    void updateById(Product product);

    /**
     * @title deleteById
     * @description 商品删除
     * @author quchenxi
     * @date 2024/1/19 14:59
     * @param id
     * @return void
     */
    void deleteById(Long id);
}
