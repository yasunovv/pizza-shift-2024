package com.yasunov.network

import com.yasunov.network.model.PizzaDTO
import retrofit2.http.GET

/*
* [API DOCUMENTATION] (https://shift-backend.onrender.com)
* */
private const val TIMEOUT = 1000L
// todo добавить в билд конфиг  https://shift-backend.onrender.com

private const val BASE_URL = "https://shift-backend.onrender.com/pizza/"

interface PizzaApi {
    @GET("pizza/catalog")
    suspend fun getCatalog(): PizzaDTO
}