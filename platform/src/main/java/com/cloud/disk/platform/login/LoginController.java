package com.cloud.disk.platform.login;

import com.cloud.disk.library.http.HttpUtils;
import com.cloud.disk.library.http.callback.CallBack;
import com.cloud.disk.library.log.LogUtils;

public class LoginController {
    public static String userId = "";

    public boolean login(String id, String password, CallBack callBack) {
        //用户登录
        LogUtils.log("login...");
        HttpUtils.post("http://login", userId);
        callBack.success("");
        return true;
    }
}
