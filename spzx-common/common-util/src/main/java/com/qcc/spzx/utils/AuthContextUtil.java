package com.qcc.spzx.utils;

import com.qcc.spzx.model.entity.system.SysUser;
import com.qcc.spzx.model.entity.user.UserInfo;

/**
 * @ClassName: AuthContextUtil
 * @Description: 操作ThreadLocal对象
 * @Date 2024/1/10 23:29
 * @Author quchenxi
 * @Version 1.0
 */
public class AuthContextUtil {

    // 创建threadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>();

    // 添加数据
    public static void set(SysUser sysUser) {
        threadLocal.set(sysUser);
    }

    // 获取数据
    public static SysUser get() {
        return threadLocal.get();
    }

    // 删除数据
    public static void remove() {
        threadLocal.remove();
    }

    // 创建threadLocal对象
    private static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();

    // 定义存储数据的静态方法
    public static void setUserInfo(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    // 定义获取数据的方法
    public static UserInfo getUserInfo() {
        return userInfoThreadLocal.get() ;
    }

    // 删除数据的方法
    public static void removeUserInfo() {
        userInfoThreadLocal.remove();
    }
}
