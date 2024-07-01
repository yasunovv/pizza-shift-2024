package com.yasunov.network

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.yasunov.network.models.ArticleDTO
import com.yasunov.network.models.Languages
import com.yasunov.network.models.SortBy
import com.yasunov.network.utils.NewsApiKeyInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

/*
* [API DOCUMENTATION] (https://newsapi.org/docs)
* */
interface NewsApi {
    //*
// Api details https://newsapi.org/docs/endpoints/everything
// */
    @GET("/everything")
    suspend fun getEverything(
        @Query("q") query: String? = null,
        @Query("from") from: Date? = null,
        @Query("language") languages: List<Languages>? = null,
        @Query("sortBy") sortBy: SortBy? = null,
        @Query("page") @IntRange(from = 0, to = 100) page: Int = 100,
        @Query("pageSize") @IntRange(from = 1) pageSize: Int = 100,

        ): Result<ArticleDTO>
}

fun NewsApi(
    baseUrl: String,
    apiKey: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
    callAdapterFactory: CallAdapter.Factory = ResultCallAdapterFactory.create(),
): NewsApi {
//    GET https://newsapi.org/v2/everything?q=bitcoin&apiKey=d15dfef831b84df0ab2c85af90e6b1be

    val modifiedOkHttpClient: OkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(NewsApiKeyInterceptor(apiKey))
        .build()

    val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                json.asConverterFactory("application/json; charset=UTF8".toMediaType())
            )
            .addCallAdapterFactory(callAdapterFactory)
            .client(modifiedOkHttpClient)
            .build()
    return retrofit.create(NewsApi::class.java)
}
