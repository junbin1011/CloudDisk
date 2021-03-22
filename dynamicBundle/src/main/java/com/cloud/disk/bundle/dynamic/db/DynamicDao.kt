package com.cloud.disk.bundle.dynamic.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cloud.disk.bundle.dynamic.Dynamic

@Dao
interface DynamicDao {
    @Query("SELECT * FROM dynamic_info")
    suspend fun getAll(): List<Dynamic>

    @Insert
    suspend fun insertAll(vararg dynamic: Dynamic)


    @Query("DELETE FROM dynamic_info")
    suspend fun deleteAll()
}