package com.yasunov.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Dough(
    @SerialName("name") val name: String,
    @SerialName("price") val price: Int
)