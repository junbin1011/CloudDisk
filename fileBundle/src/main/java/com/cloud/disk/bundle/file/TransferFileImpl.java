package com.cloud.disk.bundle.file;

import com.cloud.disk.api.file.FileInfo;
import com.cloud.disk.api.file.TransferFile;
import com.cloud.disk.api.user.UserState;
import com.cloud.disk.library.http.HttpUtils;
import com.cloud.disk.library.log.LogUtils;

import javax.inject.Inject;

public class TransferFileImpl implements TransferFile {
    @Inject
    UserState mUserState;

    @Inject
    public TransferFileImpl() {
    }

    @Override
    public FileInfo upload(String path) {
        //上传文件
        LogUtils.log("upload file");
        HttpUtils.post("http://file", mUserState.getUserId());
        return new FileInfo();
    }

    @Override
    public FileInfo download(String url) {
        //下载文件
        if (!mUserState.isLogin()) {
            return null;
        }
        return new FileInfo();
    }
}