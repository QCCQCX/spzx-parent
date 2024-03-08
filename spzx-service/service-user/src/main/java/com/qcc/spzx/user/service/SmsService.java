package com.qcc.spzx.user.service;

/**
 * @ClassName: SmsService
 * @Description: 此处输入类描述信息
 * @Date 2024/2/1 14:57
 * @Author quchenxi
 * @Version 1.0
 */
public interface SmsService {
    /**
     * @title sendCode
     * @description 发送短信验证码
     * @author quchenxi
     * @date 2024/2/1 15:01
     * @param phone
     * @return void
     */
    void sendCode(String phone);
}
