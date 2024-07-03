package com.yasunov.network.model

import kotlinx.serialization.Serializable


@Serializable
data class DoughDTO(
    val name: String,
     val price: Int
)