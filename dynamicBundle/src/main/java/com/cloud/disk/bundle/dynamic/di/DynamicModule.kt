package com.cloud.disk.bundle.dynamic.di

import com.cloud.disk.bundle.dynamic.data.local.DataSource
import com.cloud.disk.bundle.dynamic.data.local.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DynamicModule {
    @Binds
    abstract fun bindDataSource(localDataSource: LocalDataSource): DataSource
}