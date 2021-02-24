package com.cloud.disk.controller;

import com.cloud.disk.callback.CallBack;
import com.cloud.disk.model.UserInfo;
import com.cloud.disk.util.HttpUtils;
import com.cloud.disk.util.LogUtils;

public class UserController {
    public static boolean isLogin = false;
    public static String userId = "";

    public boolean login(String id, String password, CallBack callBack) {
        //用户登录
        LogUtils.log("login...");
        HttpUtils.post("http://login");
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
