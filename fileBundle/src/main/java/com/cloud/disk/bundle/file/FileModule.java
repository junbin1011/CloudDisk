package com.cloud.disk.bundle.file;

import com.cloud.disk.bundle.user.UserState;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public abstract class FileModule {
    @Binds
    public abstract TransferFile bindTransferFile(
            TransferFileImpl transferFileImpl
    );
}
