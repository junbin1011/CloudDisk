package com.cloud.disk.bundle.file;

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
