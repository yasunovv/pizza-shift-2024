package com.yasunov.network.utils

import okhttp3.Interceptor
import okhttp3.Response


class NewsApiKeyInterceptor(
    private val apiKey: String
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(
            request.newBuilder().addHeader("X-API-KEY", apiKey).build()
        )
    }
}