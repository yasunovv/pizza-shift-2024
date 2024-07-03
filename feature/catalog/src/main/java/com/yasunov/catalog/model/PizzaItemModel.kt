package com.yasunov.catalog.model

import androidx.compose.runtime.Immutable

@Immutable
data class PizzaItemModel (
    val id: Int = -1,
    val imageSrc: String,
    val name: String,
    val description: String,
    val price: Int
)