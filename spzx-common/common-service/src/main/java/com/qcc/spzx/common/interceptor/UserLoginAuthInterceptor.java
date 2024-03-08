package com.qcc.spzx.common.interceptor;

import com.alibaba.fastjson2.JSON;
import com.qcc.spzx.model.entity.user.UserInfo;
import com.qcc.spzx.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @ClassName: UserLoginAuthInterceptor
 * @Description: 此处输入类描述信息
 * @Date 2024/2/2 16:09
 * @Author quchenxi
 * @Version 1.0
 */
@Component
public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从请求头中获取token，进而得到用户信息
        String token = request.getHeader("token");
        String userJson = redisTemplate.opsForValue().get("user:spzx:" + token);

        // 放到threadLocal中
        AuthContextUtil.setUserInfo(JSON.parseObject(userJson, UserInfo.class));

//        System.out.println("*****************");
//        System.out.println(userJson);
//        System.out.println(request.getRequestURI().toString());

        return true;
    }
}