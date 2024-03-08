package com.qcc.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @ClassName: UserProperties
 * @Description: 此处输入类描述信息
 * @Date 2024/1/11 15:19
 * @Author quchenxi
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "spzx.auth")
public class UserProperties {

    private List<String> noAuthUrls;
}
