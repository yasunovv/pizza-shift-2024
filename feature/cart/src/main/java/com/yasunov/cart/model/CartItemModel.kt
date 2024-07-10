package com.yasunov.cart.model

import androidx.compose.runtime.Immutable

@Immutable
data class CartItemModel(
    val id: Int,
    val imageSrc: String,
    val name: String,
    val count: Int,
    val description: String,
    val price: Int
)
