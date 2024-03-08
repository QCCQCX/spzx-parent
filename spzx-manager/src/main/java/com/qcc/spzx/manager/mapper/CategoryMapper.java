package com.qcc.spzx.manager.mapper;

import com.qcc.spzx.model.entity.product.Category;
import com.qcc.spzx.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @ClassName: CategoryMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/1/14 14:55
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper {
    /**
     * @param id
     * @return java.util.List<com.qcc.spzx.model.entity.product.Category>
     * @title selectCategroyByParentId
     * @description 根据父id查询分类
     * @author quchenxi
     * @date 2024/1/16 09:43
     */
    List<Category> selectCategoryByParentId(Long id);

    /**
     * @title selectCountByParentId
     * @description 根据父id查询分类数量
     * @author quchenxi
     * @date 2024/1/16 09:45
     * @param id
     * @return int
     */
    int selectCountByParentId(Long id);

    /**
     * @title findAll
     * @description 查询所有分类
     * @author quchenxi
     * @date 2024/1/16 15:21
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Category>
     */
    List<Category> findAll();

    /**
     * @title batchInsert
     * @description 批量保存
     * @author quchenxi
     * @date 2024/1/16 18:17
     * @param cachedDataList
     * @return void
     */
    void batchInsert(List<CategoryExcelVo> cachedDataList);
}
