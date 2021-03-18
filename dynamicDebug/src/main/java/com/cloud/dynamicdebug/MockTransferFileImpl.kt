package com.cloud.dynamicdebug

import com.cloud.disk.api.file.FileInfo
import com.cloud.disk.api.file.TransferFile
import javax.inject.Inject

class MockTransferFileImpl @Inject constructor() : TransferFile {
    override fun upload(s: String): FileInfo {
        return FileInfo()
    }

    override fun download(s: String): FileInfo {
        return FileInfo()
    }
}