package com.cloud.disk.bundle.dynamic;


import com.cloud.disk.bundle.file.FileController;
import com.cloud.disk.bundle.user.UserStateImpl;
import com.cloud.disk.bundle.user.UserController;
import com.cloud.disk.bundle.file.FileInfo;
import com.cloud.disk.library.http.HttpUtils;
import com.cloud.disk.platform.login.LoginController;

import java.util.ArrayList;
import java.util.List;

public class DynamicController {

    FileController fileController=new FileController(new UserStateImpl());

    public boolean post(Dynamic dynamic, FileInfo fileInfo) {
        //发送一条动态消息
        if (!UserController.isLogin) {
            return false;
        }
        HttpUtils.post("http://dynamic", LoginController.userId);
        return true;
    }

    public List<Dynamic> getDynamicList() {
        //通过网络获取动态信息,有些动态带有附件需要下载
        fileController.download("");
        return new ArrayList<>();
    }
}
