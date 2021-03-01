package com.cloud.disk.bundle.user;

import com.cloud.disk.library.http.callback.CallBack;
import com.cloud.disk.library.http.HttpUtils;
import com.cloud.disk.library.log.LogUtils;

public class UserController {
    public static boolean isLogin = false;
    public static String userId = "";

    public boolean login(String id, String password, CallBack callBack) {
        //用户登录
        LogUtils.log("login...");
        HttpUtils.post("http://login", userId);
        callBack.success("");
        return true;
    }

    public UserInfo getUserInfo() {
        //获取用户信息
        UserInfo userInfo = new UserInfo();
        userId = userInfo.userId;
        return userInfo;
    }
}
