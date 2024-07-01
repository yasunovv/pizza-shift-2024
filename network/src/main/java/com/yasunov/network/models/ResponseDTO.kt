package com.yasunov.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// "status": "ok",
// "totalResults": 286,
// "articles": [ ... ]

@Serializable
data class ResponseDTO<T> (
    @SerialName("status") val status: String,
    @SerialName("totalResults") val totalResults: Int,
    @SerialName("articles") val article: List<ArticleDTO>,
)