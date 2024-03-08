package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: ProductSpecMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/17 17:13
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface ProductSpecMapper {
    /**
     * @title findByPage
     * @description 商品规格列表
     * @author quchenxi
     * @date 2024/1/17 18:34
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.ProductSpec>
     */
    List<ProductSpec> findByPage();

    /**
     * @title save
     * @description 商品规格添加
     * @author quchenxi
     * @date 2024/1/17 18:35
     * @param productSpec
     * @return void
     */
    void save(ProductSpec productSpec);

    /**
     * @title update
     * @description 商品规格修改
     * @author quchenxi
     * @date 2024/1/17 18:35
     * @param productSpec
     * @return void
     */
    void update(ProductSpec productSpec);

    /**
     * @title delete
     * @description 商品规格删除
     * @author quchenxi
     * @date 2024/1/17 18:35
     * @param id
     * @return void
     */
    void delete(Long id);

    /**
     * @title findAll
     * @description 查询所有商品规格
     * @author quchenxi
     * @date 2024/1/18 11:35
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.ProductSpec>
     */
    List<ProductSpec> findAll();
}
