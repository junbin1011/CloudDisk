package com.cloud.dynamicdebug

import android.os.Message
import com.cloud.disk.bundle.dynamic.Dynamic
import com.cloud.disk.bundle.dynamic.DynamicFragment
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.annotation.RealObject
import java.util.*

@Implements(DynamicFragment::class)
class ShadowDynamicFragment {
    @RealObject
    var dynamicFragment: DynamicFragment? = null

    enum class State {
        SUCCESS, ERROR, EMPTY
    }

    @Implementation
    fun getDynamicList() {
        val message = Message()
        if (state == State.SUCCESS) {
            val dynamicList = ArrayList<Dynamic>()
            dynamicList.add(Dynamic(1, "今天天气真不错！", 1615963675000L))
            dynamicList.add(Dynamic(2, "这个连续剧值得追！", 1615963688000L))
            message.what = 1
            message.obj = dynamicList
        } else if (state == State.ERROR) {
            message.what = 0
            message.obj = "NetworkErrorException"
        } else if (state == State.EMPTY) {
            message.what = 1
            message.obj = null
        }
        dynamicFragment!!.mHandler.sendMessage(message)
    }

    companion object {
        var state = State.SUCCESS
    }
}