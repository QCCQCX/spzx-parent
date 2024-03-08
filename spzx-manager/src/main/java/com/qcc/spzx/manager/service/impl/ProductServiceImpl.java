package com.qcc.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qcc.spzx.manager.mapper.ProductDetailsMapper;
import com.qcc.spzx.manager.mapper.ProductMapper;
import com.qcc.spzx.manager.mapper.ProductSkuMapper;
import com.qcc.spzx.manager.service.ProductService;
import com.qcc.spzx.manager.service.ProductSpecService;
import com.qcc.spzx.model.dto.product.ProductDto;
import com.qcc.spzx.model.entity.product.Product;
import com.qcc.spzx.model.entity.product.ProductDetails;
import com.qcc.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ProductServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/17 19:05
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    /**
     * @title findByPage
     * @description 商品条件分页查询
     * @author quchenxi
     * @date 2024/1/17 20:25
     * @param page
     * @param limit
     * @param productDto
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.product.Product>
     */
    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page, limit);
        List<Product> list = productMapper.findByPage(productDto);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * @title save
     * @description 商品添加
     * @author quchenxi
     * @date 2024/1/18 12:48
     * @param product
     * @return void
     */
    @Override
    public void save(Product product) {
        // 保存商品基本信息到product表
        product.setStatus(0); // 商品状态设置为0
        product.setAuditStatus(0); // 审核状态设置为0
        productMapper.save(product);

        // 获取商品SKU列表集合，保存sku信息到product_sku表
        List<ProductSku> productSkuList = product.getProductSkuList();
        for (int i = 0; i < productSkuList.size(); i++) {
            ProductSku productSku = productSkuList.get(i);

            productSku.setSkuCode(product.getId() + "_" + i); // 商品编号
            productSku.setProductId(product.getId()); // 商品ID
            productSku.setSkuName(product.getName() + productSku.getSkuSpec()); // SKU名称
            productSku.setSaleNum(0); // 销量
            productSku.setStatus(0); // 状态

            productSkuMapper.save(productSku); // SKU添加
        }

        // 保存商品详情到product_details表
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId()); // 详情id
        productDetails.setImageUrls(product.getDetailsImageUrls()); // 详情图片
        productDetailsMapper.save(); // 详情添加
    }

    /**
     * @title getById
     * @description product
     * @author quchenxi
     * @date 2024/1/18 15:34
     * @param id
     * @return com.qcc.spzx.model.entity.product.Product
     */
    @Override
    public Product getById(Long id) {
        // 根据id查询商品基本信息，product表
        Product product = productMapper.getProductById(id);

        // 根据商品id查询商品SKU列表，product_sku表
        List<ProductSku> productSkuList = productSkuMapper.findProductSkuByProductId(id);
        product.setProductSkuList(productSkuList); // 放入product中

        // 根据商品id查询商品详情数据，product_details表
        ProductDetails productDetails = productDetailsMapper.findProductDetailsByProductId(id);
        if (productDetails != null) {
            String imageUrls = productDetails.getImageUrls();
            product.setDetailsImageUrls(imageUrls); // 放入product中
        }


        return product;
    }

    /**
     * @title update
     * @description 修改商品
     * @author quchenxi
     * @date 2024/1/19 13:37
     * @param product
     * @return void
     */
    @Override
    public void update(Product product) {
        // 修改product
        productMapper.updateById(product);

        // 修改product_sku
        List<ProductSku> productSkuList = product.getProductSkuList();
        productSkuList.forEach(productSku -> {
            productSkuMapper.updateById(productSku);
        });

        // 修改product_details
        String detailsImageUrls = product.getDetailsImageUrls();
        ProductDetails productDetails = productDetailsMapper.findProductDetailsByProductId(product.getId());
        productDetails.setImageUrls(detailsImageUrls);
        productDetailsMapper.updateById(productDetails);
    }

    /**
     * @title deleteById
     * @description 商品删除
     * @author quchenxi
     * @date 2024/1/19 14:50
     * @param id
     * @return void
     */
    @Override
    public void deleteById(Long id) {
        // 删除product
        productMapper.deleteById(id);

        // 删除product_sku
        productSkuMapper.deleteByProductId(id);

        // 删除product_details
        productDetailsMapper.deleteByProductId(id);
    }

    /**
     * @title updateAuditStatus
     * @description 商品审核状态修改
     * @author quchenxi
     * @date 2024/1/19 15:25
     * @param id
     * @param auditStatus
     * @return void
     */
    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);

        // 修改指定id的商品审核状态值
        if (auditStatus == 1) {
            product.setAuditStatus(1);
            product.setAuditMessage("审核通过");
        } else {
            product.setAuditStatus(-1);
            product.setAuditMessage("审核不通过");
        }

        // 根据商品id修改商品基本信息
        productMapper.updateById(product);
    }

    /**
     * @title updateStatus
     * @description 商品状态修改
     * @author quchenxi
     * @date 2024/1/19 15:32
     * @param id
     * @param status
     * @return void
     */
    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        product.setStatus(status);
        productMapper.updateById(product);
    }
}
