package com.cloud.dynamicdebug

import com.cloud.disk.api.user.UserState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MockUserModule {
    @Binds
    abstract fun bindUserState(userStateImpl: MockUserStateImpl): UserState?
}