package com.yasunov.catalog.di

import com.yasunov.catalog.converter.PizzaCardConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PizzaCardModule {
    @Provides
    @Singleton
    fun provideConverterEntity(): PizzaCardConverter = PizzaCardConverter()
}