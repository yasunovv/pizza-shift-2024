package com.yasunov.network

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.yasunov.network.model.Pizza
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/*
* [API DOCUMENTATION] (https://shift-backend.onrender.com)
* */
private const val TIMEOUT = 1000L
private const val BASE_URL = "https://shift-backend.onrender.com"

interface PizzaApi {
    @GET("/catalog")
    suspend fun getCatalog(): Result<Pizza>
}

private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
    .build()


val pizzaApi: PizzaApi =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            Json.asConverterFactory("application/json; charset=UTF8".toMediaType())
        )
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(PizzaApi::class.java)




