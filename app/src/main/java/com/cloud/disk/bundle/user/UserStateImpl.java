package com.cloud.disk.bundle.user;

public class UserStateImpl implements UserState {
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