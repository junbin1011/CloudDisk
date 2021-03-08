package com.cloud.dynamicdebug;

import com.cloud.disk.api.user.UserState;

import javax.inject.Inject;

public class MockUserStateImpl implements UserState {
    @Inject
    public MockUserStateImpl() {
    }

    @Override
    public String getUserId() {
        return "";
    }

    @Override
    public boolean isLogin() {
        return true;
    }
}
