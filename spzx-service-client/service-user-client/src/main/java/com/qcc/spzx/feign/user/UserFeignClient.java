package com.qcc.spzx.feign.user;

import com.qcc.spzx.model.entity.user.UserAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName: UserFeignClient
 * @Description: 远程服务调用的的的客户端
 * @Date 2024/2/5 20:39
 * @Author quchenxi
 * @Version 1.0
 */
@FeignClient("service-user")
public interface UserFeignClient {
    @GetMapping("api/user/userAddress/getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable Long id);
}
