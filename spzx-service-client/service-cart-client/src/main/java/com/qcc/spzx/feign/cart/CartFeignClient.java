package com.qcc.spzx.feign.cart;

import com.qcc.spzx.model.entity.h5.CartInfo;
import com.qcc.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @ClassName: CartFeignClient
 * @Description: 此处输入类描述信息
 * @Date 2024/2/5 15:17
 * @Author quchenxi
 * @Version 1.0
 */
@FeignClient("service-cart")
public interface CartFeignClient {

    @GetMapping("/api/order/cart/auth/getAllChecked")
    public List<CartInfo> getAllChecked();

    @GetMapping("/api/order/cart/auth/deleteChecked")
    public Result deleteChecked();
}
