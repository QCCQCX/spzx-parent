package com.qcc.spzx.common.exception;

import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 此处输入类描述信息
 * @Date 2024/1/10 21:07
 * @Author quchenxi
 * @Version 1.0
 */
@ControllerAdvice // Controller的增强类
public class GlobalExceptionHandler {

    /**
     * @title error
     * @description 全局异常处理
     * @author quchenxi
     * @date 2024/1/10 21:13
     * @param
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @ResponseBody // 使得返回值为JSON格式
    @ExceptionHandler(Exception.class) // 指定出现哪种异常时执行
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
    }

    /**
     * @title error
     * @description 自定义异常处理
     * @author quchenxi
     * @date 2024/1/10 21:24
     * @param qccException
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @ResponseBody
    @ExceptionHandler(QccException.class)
    public Result error(QccException qccException) {
        return Result.build(null, qccException.getResultCodeEnum());
    }
}
