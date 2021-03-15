package com.cloud.disk.bundle.file;

import android.accounts.NetworkErrorException;

import com.cloud.disk.api.file.FileInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class FileRemoteDataSource implements FileDataSource {

    @Inject
    public FileRemoteDataSource() {
    }

    @Override
    public Flowable<List<FileInfo>> getFileList() throws NetworkErrorException {
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
        return Flowable.fromArray(infoList);
    }
}
