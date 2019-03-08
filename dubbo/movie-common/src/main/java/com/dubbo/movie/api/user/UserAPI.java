package com.dubbo.movie.api.user;

import com.stylefeng.guns.api.user.vo.UserInfoModel;
import com.stylefeng.guns.api.user.vo.UserModel;

public interface UserAPI {

    int login(String username, String password);

    boolean register(UserModel userModel);

    //检查用户是否春招
    boolean checkUsername(String username);

    //得到用户信息
    UserInfoModel getUserInfo(int uuid);

    //更新用户信息
    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);

}
