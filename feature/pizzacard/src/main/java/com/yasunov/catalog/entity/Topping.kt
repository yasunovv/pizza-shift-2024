package com.yasunov.catalog.entity

import androidx.compose.runtime.Immutable

@Immutable
data class Topping(
    val id: Int,
    val cost: Int,
    val img: String,
    val name: String
)