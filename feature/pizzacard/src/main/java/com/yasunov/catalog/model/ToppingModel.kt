package com.yasunov.catalog.model

import androidx.compose.runtime.Immutable

@Immutable
data class ToppingModel(
    val id: Int,
    val cost: Int,
    val img: String,
    val name: String
)