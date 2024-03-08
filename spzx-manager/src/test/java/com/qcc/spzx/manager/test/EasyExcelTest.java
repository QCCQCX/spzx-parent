package com.qcc.spzx.manager.test;

import com.alibaba.excel.EasyExcel;
import com.qcc.spzx.model.entity.product.Category;
import com.qcc.spzx.model.vo.product.CategoryExcelVo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: EasyExcelTest
 * @Description: 此处输入类描述信息
 * @Date 2024/1/16 13:38
 * @Author quchenxi
 * @Version 1.0
 */
public class EasyExcelTest {

    /**
     * @title testEasyExcel
     * @description 测试EasyExcel的读写操作方法
     * @author quchenxi
     * @date 2024/1/16 17:16
     * @param
     * @return void
     */
    @Test
    public void testEasyExcel() {
//        read();
//        write();
    }

    /**
     * @title read
     * @description 使用EasyExcel的读操作（需要配置监听器）
     * @author quchenxi
     * @date 2024/1/16 13:57
     * @param
     * @return void
     */
    public static void read() {
        // 定义读取excel文件位置
        String fileName = "/Users/quchenxi/Downloads/01.xlsx";
        // 创建监听器对象
        ExcelListener<CategoryExcelVo> excelListener = new ExcelListener();
        // 调用方法读取数据到监听器，参数：excel文件位置，读出数据封装对象的实体类，监听器
        EasyExcel.read(fileName, CategoryExcelVo.class, excelListener)
                .sheet().doRead();
        // 从监听器中获取读到的数据
        List<CategoryExcelVo> data = excelListener.getData();

        System.out.println(data);
    }

    /**
     * @title write
     * @description 使用EasyExcel的写操作
     * @author quchenxi
     * @date 2024/1/16 13:57
     * @param
     * @return void
     */
    public static void write() {
        List<CategoryExcelVo> list = new ArrayList<>();
        list.add(new CategoryExcelVo(1L, "数码办公", "", 0L, 1, 1));
        list.add(new CategoryExcelVo(2L, "华为手机", "", 1L, 1, 2));

        // 调用方法写入list中的数据，参数：excel文件位置，写入对象的实体类
        EasyExcel.write("/Users/quchenxi/Downloads/01.xlsx", CategoryExcelVo.class)
                // 写入到sheet的命名和要存放写入数据的list
                .sheet("分类数据").doWrite(list);

    }
}
