package com.dubbo.movie.config.auth;

import com.dubbo.movie.vo.user.UserInfoModel;

/**
 * 获取当前对象的工具类
 */
public class CurrentUser {

    // 线程绑定的存储空间
    // hystrix会出现线程切换，不能用普通的threadlocal,InheritableThreadLocal在线程切换时可以保存数据
    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
    public static void saveUserId(String userId){
        threadLocal.set(userId);
    }
    public static String getCurrentUser(){
        return threadLocal.get();
    }

     // 将用户信息放入存储空间
//    public static void saveUserInfo(UserInfoModel userInfoModel){
//        threadLocal.set(userInfoModel);
//    }
//
//     将用户信息取出
//    public static UserInfoModel getCurrentUser(){
//        return threadLocal.get();
//    }

}
