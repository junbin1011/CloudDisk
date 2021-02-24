package com.cloud.disk.controller;

import com.cloud.disk.model.FileInfo;
import com.cloud.disk.util.HttpUtils;
import com.cloud.disk.util.LogUtils;

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
