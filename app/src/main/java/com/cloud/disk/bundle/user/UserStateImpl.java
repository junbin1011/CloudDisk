package com.cloud.disk.bundle.user;

import javax.inject.Inject;

public class UserStateImpl implements UserState {

    @Inject
    public UserStateImpl() {
    }

    @Override
    public String getUserId() {
        return UserController.userId;
    }

    @Override
    public boolean isLogin() {
        return UserController.isLogin;
    }
}