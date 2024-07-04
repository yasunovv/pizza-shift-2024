package com.yasunov.catalog.model

import androidx.compose.runtime.Immutable

@Immutable
data class Size(
    val name: String,
    val price: Int
)
