package com.cloud.disk.bundle.dynamic;


import com.cloud.disk.bundle.file.FileInfo;
import com.cloud.disk.bundle.file.TransferFile;
import com.cloud.disk.bundle.user.UserState;
import com.cloud.disk.library.http.HttpUtils;
import com.cloud.disk.platform.login.LoginController;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DynamicController {

    @Inject
    TransferFile transferFile;
    @Inject
    UserState userState;

    @Inject
    public DynamicController() {
    }

    public boolean post(Dynamic dynamic, FileInfo fileInfo) {
        //发送一条动态消息
        if (!userState.isLogin()) {
            return false;
        }
        HttpUtils.post("http://dynamic", LoginController.userId);
        return true;
    }

    public List<Dynamic> getDynamicList() {
        //通过网络获取动态信息,有些动态带有附件需要下载
        //下载文件
        transferFile.download("");
        return new ArrayList<>();
    }
}
