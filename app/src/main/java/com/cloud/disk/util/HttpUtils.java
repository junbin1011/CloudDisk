package com.cloud.disk.util;

import com.cloud.disk.controller.UserController;

public class HttpUtils {
    public static void post(String url) {
        //发送http post请求，需要用到userId做标识
        String params = UserController.userId;
    }
    public static void get(String url) {
        //发送http get请求，需要用到userId做标识
        String params = UserController.userId;
    }
}
