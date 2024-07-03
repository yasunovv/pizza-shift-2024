package com.yasunov.catalog.model

import androidx.compose.runtime.Immutable

@Immutable
data class PizzaCard(
    val name: String,
    val ingredients: List<String>,
    val sizes: List<String>,
    val imageSrc: String,
    val id: Int,

    )