package com.qcc.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.qcc.spzx.common.exception.QccException;
import com.qcc.spzx.manager.listener.ExcelListener;
import com.qcc.spzx.manager.mapper.CategoryMapper;
import com.qcc.spzx.manager.service.CategoryService;
import com.qcc.spzx.model.entity.product.Category;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName: CategoryServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/14 14:54
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * @param id
     * @return java.util.List<com.qcc.spzx.model.entity.product.Category>
     * @title findCategoryList
     * @description 分类列表
     * @author quchenxi
     * @date 2024/1/16 09:13
     */
    @Override
    public List<Category> findCategoryList(Long id) {
        // 根据父id条件值进行查询，返回list集合
        List<Category> list = categoryMapper.selectCategoryByParentId(id);

        if (!CollectionUtils.isEmpty(list)) {
            // 循环判断每个分类是否有下一层分类（子分类）
            list.forEach(category -> {
                // 通过父id查询分类数量
                int count = categoryMapper.selectCountByParentId(category.getId());
                if (count > 0) {
                    category.setHasChildren(true);
                } else {
                    category.setHasChildren(false);
                }
            });
        }
        return list;
    }

    /**
     * @title exportData
     * @description 导出分类
     * @author quchenxi
     * @date 2024/1/16 15:05
     * @param response
     * @return void
     */
    @Override
    public void exportData(HttpServletResponse response) {
        try {
            // 设置响应信息，响应类型：微软-excel，编码规则：utf-8
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 定义导出文件名，设置编码规则防止中文乱码
            String fileName = URLEncoder.encode("分类数据", "UTF-8");
            // 设置响应头信息
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            // 调用mapper方法查询所有分类，返回集合list
            List<Category> categoryList = categoryMapper.findAll();

            // 将Category类型list转换成CategoryExcelVo类型list
            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>();
            for (Category category : categoryList) {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();

                // spring中提供的工具类，用于将一种类对象中的属性值复制到另一种类对象的属性域中（属性名不对应不进行复制）
                BeanUtils.copyProperties(category, categoryExcelVo);

                categoryExcelVoList.add(categoryExcelVo);
            }

            // 调用EasyExcel的write方法，写入数据到响应输出流中，根据响应头设置，以excel类型输出给浏览器
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                    .sheet("分类数据").doWrite(categoryExcelVoList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new QccException(ResultCodeEnum.DATA_ERROR);
        }
    }

    /**
     * @title importData
     * @description 导入分类
     * @author quchenxi
     * @date 2024/1/16 16:57
     * @param file
     * @return void
     */
    @Override
    public void importData(MultipartFile file) {
        // 创建监听器对象
        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener(categoryMapper);
        // 读取数据到数据库
        try {
            EasyExcel.read(file.getInputStream(), CategoryExcelVo.class, excelListener)
                    .sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new QccException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
