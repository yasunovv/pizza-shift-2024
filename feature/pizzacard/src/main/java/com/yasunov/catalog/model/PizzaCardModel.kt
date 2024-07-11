package com.yasunov.catalog.model

import androidx.compose.runtime.Immutable
import com.yasunov.designsystem.model.ToppingCardModel

@Immutable
data class PizzaCardModel(
    val id: Int,
    val description: String,
    val img: String,
    val ingredientModels: List<IngredientModel>,
    val name: String,
    val sizeModels: List<SizeModel>,
    val toppings: List<ToppingCardModel>,
)
