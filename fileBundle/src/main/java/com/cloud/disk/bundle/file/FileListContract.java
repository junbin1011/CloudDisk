package com.cloud.disk.bundle.file;

import androidx.annotation.VisibleForTesting;

import com.cloud.disk.api.file.FileInfo;

import java.util.List;

public interface FileListContract {
    interface View {
        void showFileList(List<FileInfo> fileList);

        void showNetWorkException(String errorMessage);

        void showEmptyData();
    }

    interface FilePresenter {
        @VisibleForTesting
        void getFileList();
    }
}
