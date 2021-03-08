package com.cloud.dynamicdebug;

import com.cloud.disk.api.file.TransferFile;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public abstract class MockFileModule {
    @Binds
    public abstract TransferFile bindTransferFile(
            MockTransferFileImpl transferFileImpl
    );
}
