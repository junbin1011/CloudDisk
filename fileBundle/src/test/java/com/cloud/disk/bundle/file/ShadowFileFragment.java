package com.cloud.disk.bundle.file;

import android.os.Message;

import com.cloud.disk.api.file.FileInfo;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

import java.util.ArrayList;

@Implements(FileFragment.class)
public class ShadowFileFragment {

    @RealObject
    public FileFragment fileFragment;

    enum State {
        SUCCESS,
        ERROR,
        EMPTY
    }

    public static State state = State.SUCCESS;

    @Implementation
    protected void getFileList() {
        System.out.println("shadow .... .....");
        Message message = new Message();
        if (state == State.SUCCESS) {
            ArrayList<FileInfo> infoList = new ArrayList<>();
            infoList.add(new FileInfo("遗留代码重构.pdf", 102400));
            infoList.add(new FileInfo("系统组件化.pdf", 9900));
            message.what = 1;
            message.obj = infoList;
        } else if (state == State.ERROR) {
            message.what = 0;
            message.obj = "NetworkErrorException";
        } else if (state == State.EMPTY) {
            message.what = 1;
            message.obj = null;
        }
        fileFragment.mHandler.sendMessage(message);
    }
}
