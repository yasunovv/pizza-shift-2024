package com.yasunov.data.di

import com.yasunov.data.PizzaRepository
import com.yasunov.data.conventer.ConverterPizzaCardDto
import com.yasunov.data.conventer.ConverterPizzaModelDto
import com.yasunov.network.PizzaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun providePizzaCardDtoConverter(
        @Named("BASE_URL")
        baseUrl: String
    ): ConverterPizzaCardDto {
        return ConverterPizzaCardDto(baseUrl = baseUrl)
    }

    @Provides
    @Singleton
    fun provideConverterPizzaModelDto(
        @Named("BASE_URL")
        baseUrl: String
    ): ConverterPizzaModelDto {
        return ConverterPizzaModelDto(baseUrl = baseUrl)
    }

    @Provides
    @Singleton
    fun providePizzaRepository(
        api: PizzaApi,
        converterPizzaCardDto: ConverterPizzaCardDto,
        converterPizzaModelDto: ConverterPizzaModelDto
    ): PizzaRepository {
        return PizzaRepository(
            api = api,
            converterPizzaCardDto = converterPizzaCardDto,
            converterPizzaModelDto = converterPizzaModelDto
        )
    }
}