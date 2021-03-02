package com.cloud.disk.bundle.file;

import com.cloud.disk.bundle.user.UserState;
import com.cloud.disk.library.http.HttpUtils;
import com.cloud.disk.library.log.LogUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FileController {
    private final UserState mUserState;

    @Inject
    public FileController(UserState userState) {
        this.mUserState = userState;
    }

    public List<FileInfo> getFileList() {
        return new ArrayList<>();
    }

    public FileInfo upload(String path) {
        //上传文件
        LogUtils.log("upload file");
        HttpUtils.post("http://file", mUserState.getUserId());
        return new FileInfo();
    }


    public FileInfo download(String url) {
        //下载文件
        if (!mUserState.isLogin()) {
            return null;
        }
        return new FileInfo();
    }


}
