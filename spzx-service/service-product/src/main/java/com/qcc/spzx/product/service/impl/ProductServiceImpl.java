package com.qcc.spzx.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qcc.spzx.model.dto.h5.ProductSkuDto;
import com.qcc.spzx.model.entity.product.Product;
import com.qcc.spzx.model.entity.product.ProductDetails;
import com.qcc.spzx.model.entity.product.ProductSku;
import com.qcc.spzx.model.vo.h5.ProductItemVo;
import com.qcc.spzx.product.mapper.ProductDetailsMapper;
import com.qcc.spzx.product.mapper.ProductMapper;
import com.qcc.spzx.product.mapper.ProductSkuMapper;
import com.qcc.spzx.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: ProductServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/23 15:13
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    /**
     * @title selectProductSkuBySale
     * @description 获取销量排序前十的商品
     * @author quchenxi
     * @date 2024/1/23 15:37
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.ProductSku>
     */
    @Override
    public List<ProductSku> selectProductSkuBySale() {
        return productSkuMapper.selectProductSkuBySale();
    }

    /**
     * @title findByPage
     * @description 商品列表
     * @author quchenxi
     * @date 2024/2/1 10:38
     * @param page
     * @param limit
     * @param productSkuDto
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.product.ProductSku>
     */
    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page, limit);
        List<ProductSku> list = productSkuMapper.findByPage(productSkuDto);
        return new PageInfo<>(list);
    }

    /**
     * @title item
     * @description 商品详情
     * @author quchenxi
     * @date 2024/2/1 13:38
     * @param skuId
     * @return com.qcc.spzx.model.vo.h5.ProductItemVo
     */
    @Override
    public ProductItemVo item(Long skuId) {
        // 创建vo对象
        ProductItemVo productItemVo = new ProductItemVo();

        // 根据skuId获取sku
        ProductSku productSku = productSkuMapper.getById(skuId);

        // 从sku中获取productId，进而获取商品
        Long productId = productSku.getProductId();
        Product product = productMapper.getById(productId);

        // 根据productId，获取商品详情信息
        ProductDetails productDetails = productDetailsMapper.getByProductId(productId);

        // 创建map集合，根据productId获取所有sku
        HashMap<String, Object> skuSpecValueMap = new HashMap<>();
        List<ProductSku> productSkuList = productSkuMapper.findByProductId(productId);
        productSkuList.forEach(item -> {
            // 将商品规格对应skuId放入map
            skuSpecValueMap.put(item.getSkuSpec(), item.getId());
        });

        // 封装vo
        productItemVo.setProduct(product);
        productItemVo.setProductSku(productSku);
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);
        productItemVo.setDetailsImageUrlList(
                Arrays.asList(productDetails.getImageUrls().split(",")));
        productItemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));
        return productItemVo;
    }

    /**
     * @title getBySkuId
     * @description 根据skuId查询sku信息
     * @author quchenxi
     * @date 2024/2/3 13:44
     * @param skuId
     * @return com.qcc.spzx.model.entity.product.ProductSku
     */
    @Override
    public ProductSku getBySkuId(Long skuId) {
        ProductSku productSku = productSkuMapper.getById(skuId);
        return productSku;
    }
}
