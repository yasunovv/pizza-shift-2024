package com.yasunov.cart.entity

import androidx.compose.runtime.Immutable

@Immutable
data class CartItemEntity(
    val id: Int,
    val imageSrc: String,
    val name: String,
    val count: Int,
    val description: String,
    val price: Int
)
