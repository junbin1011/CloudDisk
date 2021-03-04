package com.cloud.disk.bundle.file;

import com.cloud.disk.api.file.FileInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FileController {

    @Inject
    public FileController() {
    }

    public List<FileInfo> getFileList() {
        return new ArrayList<>();
    }


}
