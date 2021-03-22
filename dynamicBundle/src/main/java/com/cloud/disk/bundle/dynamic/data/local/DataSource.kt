package com.cloud.disk.bundle.dynamic.data.local

import com.cloud.disk.bundle.dynamic.Dynamic

interface DataSource {
    suspend fun getDynamicListFromCache(): List<Dynamic>
    suspend fun saveDynamicToCache(dynamicList: List<Dynamic>?)
}