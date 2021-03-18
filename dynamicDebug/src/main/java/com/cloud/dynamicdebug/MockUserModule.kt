package com.cloud.dynamicdebug

import com.cloud.disk.api.user.UserState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class MockUserModule {
    @Binds
    abstract fun bindUserState(userStateImpl: MockUserStateImpl): UserState?
}