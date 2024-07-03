package com.yasunov.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ToppingDTO(
    val cost: Int,
    val img: String,
    val name: String
)