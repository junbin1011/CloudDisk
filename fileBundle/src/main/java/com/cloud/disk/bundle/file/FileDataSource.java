package com.cloud.disk.bundle.file;

import android.accounts.NetworkErrorException;

import com.cloud.disk.api.file.FileInfo;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FileDataSource {
    Flowable<List<FileInfo>> getFileList() throws NetworkErrorException;
}
