package com.cloud.disk.bundle.user;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public abstract class UserModule {
    @Binds
    public abstract UserState bindUserState(
            UserStateImpl userStateImpl
    );
}
