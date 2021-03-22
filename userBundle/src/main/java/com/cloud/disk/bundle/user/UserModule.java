package com.cloud.disk.bundle.user;

import com.cloud.disk.api.user.UserState;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class UserModule {
    @Binds
    public abstract UserState bindUserState(
            UserStateImpl userStateImpl
    );
}
