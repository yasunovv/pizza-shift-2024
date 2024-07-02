package com.yasunov.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    @SerialName("cost") val cost: Int,
    @SerialName("img") val img: String,
    @SerialName("name") val name: String
)