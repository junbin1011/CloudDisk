package com.cloud.disk.bundle.dynamic

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.cloud.disk.bundle.dynamic.util.DateUtil

@Entity(tableName="dynamic_info")
data class Dynamic(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
                   @ColumnInfo(name = "content") val content: String,
                   @ColumnInfo(name = "date") val date: Long) {
    @Ignore
    val formatDate = DateUtil.getDateToString(date)
}