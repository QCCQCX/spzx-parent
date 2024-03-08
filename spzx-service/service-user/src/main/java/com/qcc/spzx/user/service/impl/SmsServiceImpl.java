package com.qcc.spzx.user.service.impl;

import com.qcc.spzx.user.service.SmsService;
import com.qcc.spzx.utils.HttpUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SmsServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/2/1 14:58
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * @param phone
     * @return void
     * @title sendCode
     * @description 发送短信验证码
     * @author quchenxi
     * @date 2024/2/1 15:01
     */
    @Override
    public void sendCode(String phone) {

        // 生成4位数字验证码
        String code = RandomStringUtils.randomNumeric(4);

        // 放到redis中，设置有效时间
        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

        // 向手机发送验证码
        sendMessage(phone, code);
    }

    /**
     * @title sendMessage
     * @description 发送短信验证码（第三方给的代码）
     * @author quchenxi
     * @date 2024/2/1 15:13
     * @param phone
     * @param code
     * @return void
     */
    private void sendMessage(String phone, String code) {
        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "dc1c03c2a8e84a1183061535747405c9";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:" + code);
        bodys.put("template_id", "CST_ptdie100");  //该模板为调试接口专用，短信下发有受限制，调试成功后请联系客服报备专属模板
        bodys.put("phone_number", phone);


        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
