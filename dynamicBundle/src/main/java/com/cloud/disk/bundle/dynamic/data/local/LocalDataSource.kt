package com.cloud.disk.bundle.dynamic.data.local

import android.content.Context
import androidx.room.Room
import com.cloud.disk.bundle.dynamic.Dynamic
import com.cloud.disk.bundle.dynamic.db.AppDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
        @ApplicationContext private var mContext: Context) : DataSource {

    private val db = Room.databaseBuilder(
            mContext,
            AppDatabase::class.java, "dynamic.db"
    ).build()


    //判断游标是否为空
    override suspend fun getDynamicListFromCache(): List<Dynamic> {
        return db.dynamicDao().getAll()
    }

    override suspend fun saveDynamicToCache(dynamicList: List<Dynamic>?) {

        dynamicList?.let {
            db.dynamicDao().deleteAll()
            db.dynamicDao().insertAll(*it.toTypedArray())
        }
    }
}