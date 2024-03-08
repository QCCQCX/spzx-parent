package com.qcc.spzx.manager.test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ExcelListener
 * @Description: EasyExcel的监听器类
 * @Date 2024/1/16 13:44
 * @Author quchenxi
 * @Version 1.0
 */
public class ExcelListener<T> extends AnalysisEventListener<T> {

    private List<T> data = new ArrayList<>();

    /**
     * @title invoke
     * @description 从第二行读取数据，封装到t对象中，供使用
     * @author quchenxi
     * @date 2024/1/16 13:46
     * @param t
     * @param analysisContext
     * @return void
     */
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        data.add(t);
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
