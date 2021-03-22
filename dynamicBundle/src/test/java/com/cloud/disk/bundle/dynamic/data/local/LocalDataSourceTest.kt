package com.cloud.disk.bundle.dynamic.data.local

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.cloud.disk.bundle.dynamic.Dynamic
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
@MediumTest
class LocalDataSourceTest {

    @Test
    fun `should get dynamic is empty when database has not data`() {
        //given
        val localDataSource = LocalDataSource(ApplicationProvider.getApplicationContext())
        //when
        val dynamicListFromCache = localDataSource.getDynamicListFromCache()
        //then
        assertThat(dynamicListFromCache).isEmpty()
    }

    @Test
    fun `should get dynamic success when database has data`() {
        //given
        val localDataSource = LocalDataSource(ApplicationProvider.getApplicationContext())
        localDataSource.saveDynamicToCache(getMockData())
        //when
        val dynamicListFromCache = localDataSource.getDynamicListFromCache()
        //then
        val dynamicOne = dynamicListFromCache[0]
        assertThat(dynamicOne.id).isEqualTo(1)
        assertThat(dynamicOne.content).isEqualTo("今天天气真不错！")
        assertThat(dynamicOne.date).isEqualTo(1615963675000L)
        val dynamicTwo = dynamicListFromCache[1]
        assertThat(dynamicTwo.id).isEqualTo(2)
        assertThat(dynamicTwo.content).isEqualTo("这个连续剧值得追！")
        assertThat(dynamicTwo.date).isEqualTo(1615963688000L)
    }


    private fun getMockData(): ArrayList<Dynamic> {
        val dynamicList = ArrayList<Dynamic>()
        dynamicList.add(Dynamic(1, "今天天气真不错！", 1615963675000L))
        dynamicList.add(Dynamic(2, "这个连续剧值得追！", 1615963688000L))
        return dynamicList
    }
}