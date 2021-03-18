package com.cloud.dynamicdebug

import com.cloud.disk.bundle.dynamic.Dynamic
import com.cloud.disk.bundle.dynamic.DynamicController
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import java.util.*

@Implements(DynamicController::class)
class ShadowDynamicController {

    enum class State {
        DATA, EMPTY
    }

    @Implementation
    fun getDynamicListFromCache(): List<Dynamic> {
        val dynamicList = ArrayList<Dynamic>()
        if (state == State.DATA) {
            dynamicList.add(Dynamic(1, "今天天气真不错！", 1615963675000L))
            dynamicList.add(Dynamic(2, "这个连续剧值得追！", 1615963688000L))
        }
        return dynamicList;
    }

    companion object {
        var state = State.EMPTY
    }
}