package com.yasunov.catalog.model

data class PizzaCatalog (
    val id: Int = -1,
    val imageSrc: String,
    val name: String,
    val description: String,
    val price: Int
)