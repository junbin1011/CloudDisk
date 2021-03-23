package com.cloud.disk.bundle.dynamic.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cloud.disk.bundle.dynamic.Dynamic
import com.cloud.disk.bundle.dynamic.db.AppDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
        @ApplicationContext private var mContext: Context) : DataSource {

    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE dynamic_info RENAME TO dynamic_info_back_up")
            database.execSQL("CREATE TABLE dynamic_info ( id  INTEGER PRIMARY KEY NOT NULL, content TEXT NOT NULL,date INTEGER NOT NULL)")
            database.execSQL("INSERT INTO dynamic_info (id, content,date) SELECT id, content,date FROM dynamic_info_back_up")
        }
    }

    private val db = Room.databaseBuilder(
            mContext,
            AppDatabase::class.java, "dynamic.db"
    ).addMigrations(MIGRATION_1_2).build()

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