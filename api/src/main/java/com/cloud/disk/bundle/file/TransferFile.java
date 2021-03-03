package com.cloud.disk.bundle.file;

public interface TransferFile {
    FileInfo upload(String path);

    FileInfo download(String url);
}
