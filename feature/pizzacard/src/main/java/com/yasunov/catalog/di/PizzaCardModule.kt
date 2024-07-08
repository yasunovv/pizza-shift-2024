package com.yasunov.catalog.di

import com.yasunov.catalog.util.PizzaCardEntityConverter
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
    fun provideConverterEntity(): PizzaCardEntityConverter = PizzaCardEntityConverter()
}