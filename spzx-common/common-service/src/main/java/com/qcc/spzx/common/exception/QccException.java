package com.qcc.spzx.common.exception;

import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @ClassName: QccException
 * @Description: 自定义的异常类
 * @Date 2024/1/10 21:14
 * @Author quchenxi
 * @Version 1.0
 */
@Data
public class QccException extends RuntimeException {

    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public QccException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
