package com.qcc.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qcc.spzx.manager.mapper.BrandMapper;
import com.qcc.spzx.manager.service.BrandService;
import com.qcc.spzx.model.entity.product.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: BrandServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/16 19:47
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * @title findByPage
     * @description 品牌列表
     * @author quchenxi
     * @date 2024/1/16 19:54
     * @param page
     * @param limit
     * @return com.github.pagehelper.PageInfo<com.qcc.spzx.model.entity.product.Brand>
     */
    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Brand> list = brandMapper.findByPage();
        PageInfo<Brand> brandPageInfo = new PageInfo<>(list);
        return brandPageInfo;
    }

    /**
     * @title save
     * @description 品牌添加
     * @author quchenxi
     * @date 2024/1/16 20:05
     * @param brand
     * @return void
     */
    @Override
    public void save(Brand brand) {
        brandMapper.save(brand);
    }

    /**
     * @title updateById
     * @description 品牌修改
     * @author quchenxi
     * @date 2024/1/16 20:13
     * @param brand
     * @return void
     */
    @Override
    public void updateById(Brand brand) {
        brandMapper.updateById(brand);
    }

    /**
     * @title deleteById
     * @description 品牌删除
     * @author quchenxi
     * @date 2024/1/16 20:27
     * @param id
     * @return void
     */
    @Override
    public void deleteById(Long id) {
        brandMapper.deleteById(id);
    }

    /**
     * @param
     * @return java.util.List<com.qcc.spzx.model.entity.product.Brand>
     * @title findAll
     * @description 查询所有品牌
     * @author quchenxi
     * @date 2024/1/16 20:53
     */
    @Override
    public List<Brand> findAll() {
        // findByPage方法查询出来的结果为全部记录，不放入到PageInfo中就不会分页处理
        return brandMapper.findByPage();
    }
}
