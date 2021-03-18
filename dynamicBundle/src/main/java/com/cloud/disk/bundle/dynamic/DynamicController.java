package com.cloud.disk.bundle.dynamic;


import android.accounts.NetworkErrorException;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.cloud.disk.api.file.FileInfo;
import com.cloud.disk.api.file.TransferFile;
import com.cloud.disk.api.user.UserState;
import com.cloud.disk.bundle.dynamic.db.DataBaseHelper;
import com.cloud.disk.library.http.HttpUtils;
import com.cloud.disk.platform.login.LoginController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class DynamicController {

    @Inject
    TransferFile transferFile;
    @Inject
    UserState userState;

    Context mContext;

    @Inject
    public DynamicController(@ApplicationContext Context context) {
        mContext = context;
    }

    public boolean post(Dynamic dynamic, FileInfo fileInfo) {
        //发送一条动态消息
        if (!userState.isLogin()) {
            return false;
        }
        HttpUtils.post("http://dynamic", LoginController.userId);
        return true;
    }

    public List<Dynamic> getDynamicList() throws NetworkErrorException {
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
        //下载文件
        transferFile.download("");
        ArrayList<Dynamic> dynamicList = new ArrayList<>();
        dynamicList.add(new Dynamic(1, "今天天气真不错！", 1615963675000L));
        dynamicList.add(new Dynamic(2, "这个连续剧值得追！", 1615963688000L));
        return dynamicList;
    }

    public List<Dynamic> getDynamicListFromCache() {
        List<Dynamic> dynamicList = new ArrayList<>();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
        Cursor c = dataBaseHelper.getWritableDatabase().query(DataBaseHelper.dynamic_info, null, null, null, null, null, null);
        if (c.moveToFirst()) {//判断游标是否为空
            for (int i = 0; i < c.getCount(); i++) {
                c.move(i);//移动到指定记录
                int id = c.getInt(c.getColumnIndex(DataBaseHelper.id));
                String content = c.getString(c.getColumnIndex(DataBaseHelper.content));
                long date = c.getLong(c.getColumnIndex(DataBaseHelper.date));
                dynamicList.add(new Dynamic(id, content, date));
            }
        }
        return dynamicList;
    }

    public void saveDynamicToCache(List<Dynamic> dynamicList) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext);
        if (dynamicList != null && dynamicList.size() > 0) {
            dataBaseHelper.getWritableDatabase().delete(DataBaseHelper.dynamic_info, null, null);
            for (Dynamic dynamic : dynamicList) {
                ContentValues cv = new ContentValues();
                cv.put(DataBaseHelper.id, dynamic.id);
                cv.put(DataBaseHelper.content, dynamic.content);
                cv.put(DataBaseHelper.date, dynamic.date);
                dataBaseHelper.getWritableDatabase().insert(DataBaseHelper.dynamic_info, null, cv);
            }
        }
    }
}
