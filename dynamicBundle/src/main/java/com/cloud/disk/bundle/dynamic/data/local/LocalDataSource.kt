package com.cloud.disk.bundle.dynamic.data.local

import android.content.ContentValues
import android.content.Context
import com.cloud.disk.bundle.dynamic.Dynamic
import com.cloud.disk.bundle.dynamic.db.DataBaseHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class LocalDataSource @Inject constructor(
        @ApplicationContext private var mContext: Context) : DataSource {

    //判断游标是否为空
    override fun getDynamicListFromCache(): List<Dynamic> {
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

    override fun saveDynamicToCache(dynamicList: List<Dynamic>?) {
        val dataBaseHelper = DataBaseHelper(mContext)
        dynamicList?.let {
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