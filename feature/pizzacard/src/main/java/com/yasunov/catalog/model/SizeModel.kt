package com.yasunov.catalog.model

import androidx.compose.runtime.Immutable

@Immutable
data class SizeModel(
    val id: Int,
    val name: String,
    val price: Int
)
