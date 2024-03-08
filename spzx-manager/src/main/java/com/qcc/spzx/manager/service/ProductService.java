package com.qcc.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.qcc.spzx.model.dto.product.ProductDto;
import com.qcc.spzx.model.entity.product.Product;

/**
 * @ClassName: ProductService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/17 19:05
 * @Author quchenxi
 * @Version 1.0
 */
public interface ProductService {
    /**
     * @param page
     * @param limit
     * @param productDto
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.product.Product>
     * @title findByPage
     * @description 商品条件分页查询
     * @author quchenxi
     * @date 2024/1/17 20:22
     */
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);

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
     * @title getById
     * @description product
     * @author quchenxi
     * @date 2024/1/18 15:34
     * @param id
     * @return com.qcc.spzx.model.entity.product.Product
     */
    Product getById(Long id);

    /**
     * @title update
     * @description 修改商品
     * @author quchenxi
     * @date 2024/1/19 13:37
     * @param product
     * @return void
     */
    void update(Product product);

    /**
     * @title deleteById
     * @description 商品删除
     * @author quchenxi
     * @date 2024/1/19 14:49
     * @param id
     * @return void
     */
    void deleteById(Long id);

    /**
     * @title updateAuditStatus
     * @description 商品审核状态修改
     * @author quchenxi
     * @date 2024/1/19 15:25
     * @param id
     * @param auditStatus
     * @return void
     */
    void updateAuditStatus(Long id, Integer auditStatus);

    /**
     * @title updateStatus
     * @description 商品状态修改
     * @author quchenxi
     * @date 2024/1/19 15:32
     * @param id
     * @param status
     * @return void
     */
    void updateStatus(Long id, Integer status);
}
