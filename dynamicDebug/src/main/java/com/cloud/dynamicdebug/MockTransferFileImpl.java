package com.cloud.dynamicdebug;

import com.cloud.disk.api.file.FileInfo;
import com.cloud.disk.api.file.TransferFile;

import javax.inject.Inject;

public class MockTransferFileImpl implements TransferFile {
    @Inject
    public MockTransferFileImpl() {
    }

    @Override
    public FileInfo upload(String s) {
        return new FileInfo();
    }

    @Override
    public FileInfo download(String s) {
        return new FileInfo();
    }
}
