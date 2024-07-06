package com.yasunov.catalog.model

import androidx.compose.runtime.Immutable
import com.yasunov.designsystem.model.ToppingCardModel

@Immutable
data class PizzaCard(
    val id: Int,
    val description: String,
    val img: String,
    val ingredients: List<Ingredient>,
    val name: String,
    val sizes: List<Size>,
    val toppings: List<ToppingCardModel>,
)
