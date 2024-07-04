package com.yasunov.catalog.model

import androidx.compose.runtime.Immutable

@Immutable
data class Ingredient(
    val cost: Int,
    val img: String,
    val name: String
)