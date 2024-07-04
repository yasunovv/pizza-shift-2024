package com.yasunov.network.di

import android.content.Context
import coil.ImageLoader
import com.yasunov.network.PizzaApi
import com.yasunov.network.pizzaApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext application: Context,
    ): ImageLoader =
        ImageLoader.Builder(application)
            .respectCacheHeaders(false)
            .build()

    @Provides
    @Singleton
    fun providePizzaApi(): PizzaApi {
        return pizzaApi
    }
//    @Provides
//    @Singleton
//    fun provideInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            val request: Request = chain.request()
//
//            LOGGER.info(
//                "Intercepted headers: {} from URL: {}",
//                request.headers(),
//                request.url()
//            )
//
//            chain.proceed(request)
//        }
//    }
}


