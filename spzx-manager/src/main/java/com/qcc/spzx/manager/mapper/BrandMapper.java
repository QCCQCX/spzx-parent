package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: BrandMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/16 19:46
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface BrandMapper {
    /**
     * @title findByPage
     * @description 品牌列表
     * @author quchenxi
     * @date 2024/1/16 19:57
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Brand>
     */
    List<Brand> findByPage();

    /**
     * @title save
     * @description 品牌添加
     * @author quchenxi
     * @date 2024/1/16 20:06
     * @param brand
     * @return void
     */
    void save(Brand brand);

    /**
     * @param
     * @param brand
     * @return void
     * @title updateById
     * @description 品牌修改
     * @author quchenxi
     * @date 2024/1/16 20:13
     */
    void updateById(Brand brand);

    /**
     * @title deleteById
     * @description 品牌删除
     * @author quchenxi
     * @date 2024/1/16 20:28
     * @param id
     * @return void
     */
    void deleteById(Long id);
}
