package com.cloud.dynamicdebug

import android.os.Message
import com.cloud.disk.bundle.dynamic.Dynamic
import com.cloud.disk.bundle.dynamic.DynamicViewModel
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.annotation.RealObject
import java.util.*

@Implements(DynamicViewModel::class)
class ShadowDynamicViewModel {
    @RealObject
    lateinit var dynamicViewModel: DynamicViewModel

    enum class State {
        SUCCESS, ERROR, EMPTY, CACHE
    }

    @Implementation
    fun getDynamicList() {
        val message = Message()
        when (state) {
            State.SUCCESS -> {
                val dynamicList = getMockData()
                dynamicViewModel.dynamicListLiveData.value = dynamicList
            }
            State.ERROR -> {
                dynamicViewModel.errorMessageLiveData.value = "NetworkErrorException"
            }
            State.EMPTY -> {
                dynamicViewModel.dynamicListLiveData.value = null
            }
            State.CACHE -> {
                val dynamicList = getMockData()
                dynamicViewModel.dynamicListLiveData.value = dynamicList
            }
        }
    }

    private fun getMockData(): ArrayList<Dynamic> {
        val dynamicList = ArrayList<Dynamic>()
        dynamicList.add(Dynamic(1, "今天天气真不错！", 1615963675000L))
        dynamicList.add(Dynamic(2, "这个连续剧值得追！", 1615963688000L))
        return dynamicList
    }

    companion object {
        var state = State.SUCCESS
    }
}