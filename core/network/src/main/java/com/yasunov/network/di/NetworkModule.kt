package com.yasunov.network.di

import android.content.Context
import coil.ImageLoader
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.yasunov.network.PizzaApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module(includes = [NetworkBindModule::class])
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TIMEOUT = 1000L

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
    fun providePizzaApi(
        @Named("BASE_URL")
        baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): PizzaApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(PizzaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return Json.asConverterFactory("application/json; charset=UTF8".toMediaType())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
    @Provides
    @Singleton
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    @Named("BASE_URL")
    fun provideBaseUrl(): String {
        return "https://shift-backend.onrender.com"
    }

}

@Module
@InstallIn(SingletonComponent::class)
interface NetworkBindModule {
    @Binds
    @Singleton
    fun bindInterceptor(httpLoggingInterceptor: HttpLoggingInterceptor): Interceptor
}
