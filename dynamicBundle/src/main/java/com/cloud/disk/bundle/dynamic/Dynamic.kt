package com.cloud.disk.bundle.dynamic

import com.cloud.disk.bundle.dynamic.util.DateUtil

data class Dynamic(val id: Int, val content: String, val date: Long) {
    val formatDate = DateUtil.getDateToString(date)
}