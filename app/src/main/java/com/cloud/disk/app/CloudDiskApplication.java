package com.cloud.disk.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class CloudDiskApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
    }
}
