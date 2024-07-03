package com.yasunov.catalog.model

data class PizzaCard(
    val name: String,
    val ingredients: List<String>,
    val sizes: List<String>,
    val imageSrc: String,
)