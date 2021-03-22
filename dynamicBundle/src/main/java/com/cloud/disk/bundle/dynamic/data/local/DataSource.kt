package com.cloud.disk.bundle.dynamic.data.local

import com.cloud.disk.bundle.dynamic.Dynamic

interface DataSource {
    fun getDynamicListFromCache(): List<Dynamic>
    fun saveDynamicToCache(dynamicList: List<Dynamic>?)
}