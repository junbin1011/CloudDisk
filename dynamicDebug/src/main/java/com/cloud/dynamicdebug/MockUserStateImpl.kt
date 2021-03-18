package com.cloud.dynamicdebug

import com.cloud.disk.api.user.UserState
import javax.inject.Inject

class MockUserStateImpl @Inject constructor() : UserState {
    override fun getUserId(): String {
        return ""
    }

    override fun isLogin(): Boolean {
        return true
    }
}