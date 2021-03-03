package com.cloud.disk.bundle.user;

import com.cloud.disk.platform.login.LoginController;

public class UserController {
    public static boolean isLogin = false;

    public UserInfo getUserInfo() {
        //获取用户信息
        UserInfo userInfo = new UserInfo();
        LoginController.userId = userInfo.userId;
        return userInfo;
    }
}
