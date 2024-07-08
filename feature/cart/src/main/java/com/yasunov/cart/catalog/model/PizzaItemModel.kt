package com.yasunov.cart.catalog.model

import kotlinx.serialization.Serializable
 
@Serializable
data class PizzaItemModel(
    val id: Int = -1,
    val imageSrc: String,
    val name: String,
    val description: String,
    val price: Int
)