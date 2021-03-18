package com.cloud.disk.bundle.dynamic

import android.accounts.NetworkErrorException
import android.content.ContentValues
import android.content.Context
import com.cloud.disk.api.file.FileInfo
import com.cloud.disk.api.file.TransferFile
import com.cloud.disk.api.user.UserState
import com.cloud.disk.bundle.dynamic.db.DataBaseHelper
import com.cloud.disk.library.http.HttpUtils
import com.cloud.disk.platform.login.LoginController
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class DynamicController @Inject constructor(@ApplicationContext var mContext: Context) {

    @Inject
    lateinit var transferFile: TransferFile
    
    @Inject
    lateinit var userState: UserState

    fun post(dynamic: Dynamic, fileInfo: FileInfo): Boolean {
        //发送一条动态消息
        if (!userState.isLogin) {
            return false
        }
        HttpUtils.post("http://dynamic", LoginController.userId)
        return true
    }

    fun getDynamicList(): List<Dynamic> {
        //模拟网络延时
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        //模拟随机网络异常
        val random = Random()
        val num = random.nextInt(100)
        if (num % 3 == 0) {
            throw NetworkErrorException()
        }
        //下载文件
        transferFile.download("")
        val dynamicList = ArrayList<Dynamic>()
        dynamicList.add(Dynamic(1, "今天天气真不错！", 1615963675000L))
        dynamicList.add(Dynamic(2, "这个连续剧值得追！", 1615963688000L))
        return dynamicList
    }

    //判断游标是否为空
    fun getDynamicListFromCache(): List<Dynamic> {
        val dynamicList: MutableList<Dynamic> = ArrayList()
        val dataBaseHelper = DataBaseHelper(mContext)
        val c = dataBaseHelper.writableDatabase.query(DataBaseHelper.dynamic_info, null, null, null, null, null, null)
        if (c.moveToFirst()) { //判断游标是否为空
            for (i in 0 until c.count) {
                c.move(i) //移动到指定记录
                val id = c.getInt(c.getColumnIndex(DataBaseHelper.id))
                val content = c.getString(c.getColumnIndex(DataBaseHelper.content))
                val date = c.getLong(c.getColumnIndex(DataBaseHelper.date))
                dynamicList.add(Dynamic(id, content, date))
            }
        }
        return dynamicList
    }

    fun saveDynamicToCache(dynamicList: List<Dynamic>) {
        val dataBaseHelper = DataBaseHelper(mContext)
        if (dynamicList.isNotEmpty()) {
            dataBaseHelper.writableDatabase.delete(DataBaseHelper.dynamic_info, null, null)
            for ((id, content, date) in dynamicList) {
                val cv = ContentValues()
                cv.put(DataBaseHelper.id, id)
                cv.put(DataBaseHelper.content, content)
                cv.put(DataBaseHelper.date, date)
                dataBaseHelper.writableDatabase.insert(DataBaseHelper.dynamic_info, null, cv)
            }
        }
    }
}