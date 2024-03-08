package com.qcc.spzx.product.mapper;

import com.qcc.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: BrandMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/28 20:49
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface BrandMapper {
    /**
     * @title findAll
     * @description 获取所有品牌
     * @author quchenxi
     * @date 2024/1/28 20:56
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Brand>
     */
    List<Brand> findAll();
}
