package com.cloud.disk.bundle.dynamic.data


import android.content.Context
import com.cloud.disk.api.file.FileInfo
import com.cloud.disk.api.file.TransferFile
import com.cloud.disk.api.user.UserState
import com.cloud.disk.bundle.dynamic.Dynamic
import com.cloud.disk.bundle.dynamic.data.local.DataSource
import com.cloud.disk.bundle.dynamic.exception.NetWorkErrorException
import com.cloud.disk.library.http.HttpUtils
import com.cloud.disk.platform.login.LoginController
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject

class DynamicRepository @Inject constructor(
        @ApplicationContext private var mContext: Context,
        private val transferFile: TransferFile,
        private val userState: UserState,
        private val dataSource: DataSource) {


    fun post(dynamic: Dynamic, fileInfo: FileInfo): Boolean {
        //发送一条动态消息
        if (!userState.isLogin) {
            return false
        }
        HttpUtils.post("http://dynamic", LoginController.userId)
        return true
    }


    @Throws(NetWorkErrorException::class)
    suspend fun getDynamicList(): List<Dynamic> {
        //模拟网络延时
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        delay(1000)
        //模拟随机网络异常
        val random = Random()
        val num = random.nextInt(100)
        if (num % 3 == 0) {
            throw NetWorkErrorException()
        }
        //下载文件
        transferFile.download("")
        val dynamicList = ArrayList<Dynamic>()
        dynamicList.add(Dynamic(1, "今天天气真不错！", 1615963675000L))
        dynamicList.add(Dynamic(2, "这个连续剧值得追！", 1615963688000L))
        return dynamicList
    }

    //判断游标是否为空
    suspend fun getDynamicListFromCache(): List<Dynamic> {
        return dataSource.getDynamicListFromCache()
    }

    suspend fun saveDynamicToCache(dynamicList: List<Dynamic>?) {
        dataSource.saveDynamicToCache(dynamicList)
    }
}