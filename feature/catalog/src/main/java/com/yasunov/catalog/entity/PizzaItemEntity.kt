package com.yasunov.catalog.entity

import androidx.compose.runtime.Immutable

@Immutable
data class PizzaItemEntity(
    val id: Int,
    val imageSrc: String,
    val name: String,
    val description: String,
    val price: Int
)
