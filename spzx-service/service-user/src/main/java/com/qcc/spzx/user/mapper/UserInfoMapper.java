package com.qcc.spzx.user.mapper;

import com.qcc.spzx.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: UserInfoMapper
 * @Description: 此处输入类描述信息
 * @Date 2024/2/1 18:45
 * @Author quchenxi
 * @Version 1.0
 */
@Mapper
public interface UserInfoMapper {
    /**
     * @title selectByUsername
     * @description 根据用户名查询用户
     * @author quchenxi
     * @date 2024/2/1 19:05
     * @param username
     * @return com.qcc.spzx.model.entity.user.UserInfo
     */
    UserInfo selectByUsername(String username);

    /**
     * @title save
     * @description 用户添加
     * @author quchenxi
     * @date 2024/2/1 19:13
     * @param userInfo
     * @return void
     */
    void save(UserInfo userInfo);
}
