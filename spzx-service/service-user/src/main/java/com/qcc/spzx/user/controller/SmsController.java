package com.qcc.spzx.user.controller;

import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.user.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: SmsController
 * @Description: 短信验证码接口
 * @Date 2024/2/1 14:57
 * @Author quchenxi
 * @Version 1.0
 */
@Tag(name = "短信验证码接口")
@RestController
@RequestMapping("/api/user/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    /**
     * @title sendCode
     * @description 发送短信验证码
     * @author quchenxi
     * @date 2024/2/1 15:00
     * @param phone
     * @return com.qcc.spzx.model.vo.common.Result
     */
    @Operation(summary = "短信验证码")
    @GetMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable String phone) {
        smsService.sendCode(phone);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
