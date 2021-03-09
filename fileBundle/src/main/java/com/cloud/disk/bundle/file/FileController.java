package com.cloud.disk.bundle.file;

import android.accounts.NetworkErrorException;
import android.os.Looper;
import android.os.NetworkOnMainThreadException;

import androidx.annotation.MainThread;

import com.cloud.disk.api.file.FileInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

public class FileController {

    @Inject
    public FileController() {
    }

    public List<FileInfo> getFileList() throws NetworkErrorException {
        //模拟网络方法 不能在主线程调用
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            throw new NetworkOnMainThreadException();
        }
        //模拟网络延时
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //模拟随机网络异常
        Random random = new Random();
        int num = random.nextInt(100);
        if (num % 3 == 0) {
            throw new NetworkErrorException();
        }
        ArrayList<FileInfo> infoList = new ArrayList<>();
        infoList.add(new FileInfo("遗留代码重构.pdf", 102400));
        infoList.add(new FileInfo("系统组件化.pdf", 9900));
        return infoList;
    }
}
