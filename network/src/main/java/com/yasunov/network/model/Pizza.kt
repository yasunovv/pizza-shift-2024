package com.yasunov.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pizza(
    @SerialName("catalog") val catalog: List<Catalog>,
    @SerialName("status") val success: Boolean
)