package com.cloud.disk.bundle.file;

import com.cloud.disk.api.file.TransferFile;

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
