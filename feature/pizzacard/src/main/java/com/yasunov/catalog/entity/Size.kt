package com.yasunov.catalog.entity

import androidx.compose.runtime.Immutable

@Immutable
data class Size(
    val id: Int,
    val name: String,
    val price: Int
)
