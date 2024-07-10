package com.yasunov.data.di

import com.yasunov.data.conventer.ConverterPizzaCardDto
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
    ): ConverterPizzaCardDto {
        return ConverterPizzaCardDto(baseUrl = baseUrl)
    }


}