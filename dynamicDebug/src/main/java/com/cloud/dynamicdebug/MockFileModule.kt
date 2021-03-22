package com.cloud.dynamicdebug

import com.cloud.disk.api.file.TransferFile
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MockFileModule {
    @Binds
    abstract fun bindTransferFile(transferFileImpl: MockTransferFileImpl): TransferFile?
}