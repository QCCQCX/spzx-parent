package com.qcc.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.qcc.spzx.manager.mapper.CategoryMapper;
import com.qcc.spzx.model.vo.product.CategoryExcelVo;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @ClassName: ExcelListener
 * @Description: 用于EasyExcel读取数据的监听器
 * @Date 2024/1/16 17:01
 * @Author quchenxi
 * @Version 1.0
 */
public class ExcelListener<T> implements ReadListener<T> {


    private CategoryMapper categoryMapper;

    /**
     * @title ExcelListener
     * @description 构造方法，由于EasyExcel不能被spring管理，所以不能注入，只能通过构造传入对象
     * @author quchenxi
     * @date 2024/1/16 17:21
     * @param categoryMapper
     * @return
     */
    public ExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    /**
     * 缓存的数据
     */
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    /**
     * @title invoke
     * @description 从第二行读取数据，封装到T类型的对象t中
     * @author quchenxi
     * @date 2024/1/16 17:17
     * @param t
     * @param analysisContext
     * @return void
     */
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        // 将t加入缓存列表中
        cachedDataList.add(t);
        // 数据条数达到BATCH_COUNT，存入数据库，防止OOM（因此需要引入CategoryMapper操作数据库）
        if (cachedDataList.size() >= BATCH_COUNT) {
            // 调用方法，批量添加到数据库
            saveData();
            // 清理缓存（更新缓存列表数据）
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * @title doAfterAllAnalysed
     * @description 所有数据读取完毕后执行
     * @author quchenxi
     * @date 2024/1/16 18:15
     * @param analysisContext
     * @return void
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 将剩余数据批量存入数据库
        saveData();
    }

    /**
     * @title saveData
     * @description 批量保存数据到数据库
     * @author quchenxi
     * @date 2024/1/16 18:15
     * @param
     * @return void
     */
    private void saveData() {
        categoryMapper.batchInsert((List<CategoryExcelVo>) cachedDataList);
    }
}
