package com.cloud.disk.bundle.dynamic.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cloud.disk.bundle.dynamic.Dynamic

@Database(entities = arrayOf(Dynamic::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dynamicDao(): DynamicDao
}
