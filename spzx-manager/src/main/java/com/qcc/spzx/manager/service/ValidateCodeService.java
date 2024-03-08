package com.qcc.spzx.manager.service;

import com.qcc.spzx.model.vo.system.ValidateCodeVo;

/**
 * @ClassName: ValidateCodeService
 * @Description: 此处输入类描述信息
 * @Date 2024/1/10 21:42
 * @Author quchenxi
 * @Version 1.0
 */
public interface ValidateCodeService {

    ValidateCodeVo generateValidateCode();
}
