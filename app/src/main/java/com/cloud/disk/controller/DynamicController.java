package com.cloud.disk.controller;


import com.cloud.disk.model.Dynamic;
import com.cloud.disk.model.FileInfo;
import com.cloud.disk.util.HttpUtils;

import java.util.ArrayList;
import java.util.List;

public class DynamicController {

    FileController fileController=new FileController();

    public boolean post(Dynamic dynamic, FileInfo fileInfo) {
        //发送一条动态消息
        if (!UserController.isLogin) {
            return false;
        }
        HttpUtils.post("http://dynamic");
        return true;
    }

    public List<Dynamic> getDynamicList() {
        //通过网络获取动态信息,有些动态带有附件需要下载
        fileController.download("");
        return new ArrayList<>();
    }
}
