package com.yasunov.network.model

import kotlinx.serialization.Serializable

@Serializable
data class IngredientDTO(
    val cost: Int,
    val img: String,
    val name: String
)