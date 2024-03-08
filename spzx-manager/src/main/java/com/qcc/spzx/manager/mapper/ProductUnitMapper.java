package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: ProductUnitMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/18 11:02
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface ProductUnitMapper {
    /**
     * @title findAll
     * @description 查询所有商品计量单位
     * @author quchenxi
     * @date 2024/1/18 11:14
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.base.ProductUnit>
     */
    List<ProductUnit> findAll();
}
