package com.qcc.spzx.manager.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.qcc.spzx.model.entity.system.SysUser;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import com.qcc.spzx.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: LoginAuthInterceptor
 * @Description: 自定义拦截器
 * @Date 2024/1/10 23:34
 * @Author quchenxi
 * @Version 1.0
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println(request.getRequestURL().toString());

        // 获取请求方式
        String method = request.getMethod();

        // 若请求方式为options即预检请求，放行
        if ("OPTIONS".equals(method)) {
            return true;
        }

        // 从请求头中获取token
        String token = request.getHeader("token");

        // 若token为空，返回错误提示
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response); // 自定义的没有登陆响应码208方法，相应前端（在下边定义的）
            return false;
        }

        // 若token不为空，到redis中查询此token
        String userInfoString = redisTemplate.opsForValue().get("user:login" + token);

        // 若redis查询不到数据，返回错误提示
        if (StrUtil.isEmpty(userInfoString)) {
            responseNoLoginInfo(response);
            return false;
        }

        // 若redis查询到了数据，将用户信息放到ThreadLocal中
        AuthContextUtil.set(JSON.parseObject(userInfoString, SysUser.class));

        // 延长redis用户信息过期时间
        redisTemplate.expire("user:login" + token, 30, TimeUnit.MINUTES);

        // 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 将ThreadLocal中的数据删除
        AuthContextUtil.remove();
    }

    /**
     * @title responseNoLoginInfo
     * @description 响应208状态码给前端
     * @author quchenxi
     * @date 2024/1/10 23:42
     * @param response
     * @return void
     */
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }
}
