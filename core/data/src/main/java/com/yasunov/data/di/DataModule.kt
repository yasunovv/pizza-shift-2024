package com.yasunov.data.di

import com.yasunov.data.conventer.ConverterDto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {
    @Provides
    @Singleton
    fun provideDtoConverter (
        @Named("BASE_URL")
        baseUrl: String
    ): ConverterDto {
        return ConverterDto(baseUrl = baseUrl)
    }
}