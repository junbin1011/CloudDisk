package com.cloud.disk.bundle.user;

import com.cloud.disk.api.user.UserState;
import com.cloud.disk.platform.login.LoginController;

import javax.inject.Inject;

public class UserStateImpl implements UserState {

    @Inject
    public UserStateImpl() {
    }

    @Override
    public String getUserId() {
        return LoginController.userId;
    }

    @Override
    public boolean isLogin() {
        return UserController.isLogin;
    }
}