package com.qcc.spzx.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qcc.spzx.model.entity.user.UserInfo;
import com.qcc.spzx.model.vo.common.Result;
import com.qcc.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @ClassName: AuthGlobalFilter
 * @Description: 全局过滤器
 * @Date 2024/2/2 15:04
 * @Author quchenxi
 * @Version 1.0
 */
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 用于路径匹配
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求路径
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // 判断路径规则，登录校验
        if (antPathMatcher.match("/api/**/auth/**", path)) {
            UserInfo userInfo = this.getUserInfo(request);
            if (userInfo == null) {
                ServerHttpResponse response = exchange.getResponse();
                return out(response, ResultCodeEnum.LOGIN_AUTH);
            }
        }

        // 放行
        return chain.filter(exchange);
    }

    /**
     * @title getUserInfo
     * @description 请求头中取token，查redis，返回用户信息
     * @author quchenxi
     * @date 2024/2/2 15:16
     * @param request
     * @return com.qcc.spzx.model.entity.user.UserInfo
     */
    private UserInfo getUserInfo(ServerHttpRequest request) {
        // 从请求头中获取token值
        String token = null;
        List<String> tokenList = request.getHeaders().get("token");
        if (tokenList != null) {
            token = tokenList.get(0);
        }

        // 判空
        if (!StringUtils.isEmpty(token)) {
            // 根据token查redis
            String userJson = redisTemplate.opsForValue().get("user:spzx:" + token);
            if (StringUtils.isEmpty(userJson)) {
                return null;
            } else {
                UserInfo userInfo = JSON.parseObject(userJson, UserInfo.class);
                return userInfo;
            }
        }
        return null;
    }

    /**
     * @title out
     * @description 输出登录校验的错误提示
     * @author quchenxi
     * @date 2024/2/2 15:13
     * @param response
     * @param resultCodeEnum
     * @return reactor.core.publisher.Mono<java.lang.Void>
     */
    private Mono<Void> out(ServerHttpResponse response, ResultCodeEnum resultCodeEnum) {
        Result result = Result.build(null, resultCodeEnum);
        byte[] bits = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
