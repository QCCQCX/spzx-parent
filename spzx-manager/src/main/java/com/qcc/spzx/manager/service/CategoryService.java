package com.qcc.spzx.manager.service;

import com.qcc.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName: CategoryService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/14 14:54
 * @Author quchenxi
 * @Version 1.0
 */
public interface CategoryService {
    /**
     * @title findCategoryList
     * @description 分类列表
     * @author quchenxi
     * @date 2024/1/16 09:12
     * @param id
     * @return java.util.List<com.qcc.spzx.model.entity.product.Category>
     */
    List<Category> findCategoryList(Long id);

    /**
     * @title exportData
     * @description 导出分类
     * @author quchenxi
     * @date 2024/1/16 15:04
     * @param response
     * @return void
     */
    void exportData(HttpServletResponse response);

    /**
     * @title importData
     * @description 导入分类
     * @author quchenxi
     * @date 2024/1/16 16:57
     * @param file
     * @return void
     */
    void importData(MultipartFile file);
}
