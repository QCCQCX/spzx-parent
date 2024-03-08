package com.qcc.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.qcc.spzx.manager.service.ValidateCodeService;
import com.qcc.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ValidateCodeServiceImpl
 * @Description: 此处输入类描述信息
 * @Date 2024/1/10 21:42
 * @Author quchenxi
 * @Version 1.0
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * @title generateValidateCode
     * @description 生成图片验证码
     * @author quchenxi
     * @date 2024/1/10 21:48
     * @param
     * @return com.qcc.spzx.model.vo.system.ValidateCodeVo
     */
    @Override
    public ValidateCodeVo generateValidateCode() {
        // 生成图片验证码（通过hutool），参数：验证码图片宽、高、位数、干扰线数量
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 2);
        String code = circleCaptcha.getCode(); // 获取字符串类型的验证码值
        String imageBase64 = circleCaptcha.getImageBase64(); // 获取图片类型的验证码，编码方式为base64

        // 将验证码存储到redis里面
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue()
                .set("user:validate" + key, // 设置key
                        code, // 验证码的值
                        5, // 有效时长
                        TimeUnit.MINUTES); // 有效时长的单位

        // 返回ValidateCodeVo对象
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(key); // redis存储图片的key
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64); // 传递给前端的图片固定格式
        return validateCodeVo;
    }
}
