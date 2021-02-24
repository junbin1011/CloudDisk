package com.cloud.disk.bundle.file;

import com.cloud.disk.bundle.user.UserController;
import com.cloud.disk.library.http.HttpUtils;
import com.cloud.disk.library.log.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class FileController {
    public List<FileInfo> getFileList() {
        return new ArrayList<>();
    }

    public FileInfo upload(String path) {
        //上传文件
        LogUtils.log("upload file");
        HttpUtils.post("http://file");
        return new FileInfo();
    }

    public FileInfo download(String url) {
        //下载文件
        if (!UserController.isLogin) {
            return null;
        }
        return new FileInfo();
    }
}
