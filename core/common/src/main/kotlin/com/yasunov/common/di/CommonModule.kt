package com.yasunov.common.di

import com.yasunov.common.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
public class CommonModule {
    @Provides
    @Singleton
    public fun provideAppCoroutineDispatchers(): AppDispatchers = AppDispatchers()

}
