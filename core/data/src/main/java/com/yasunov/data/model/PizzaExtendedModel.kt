package com.yasunov.data.model

import com.yasunov.model.IngredientModel
import com.yasunov.model.SizeModel
import com.yasunov.model.ToppingModel

data class PizzaExtendedModel(
    val id: Int,
    val description: String,
    val img: String,
    val ingredients: List<IngredientModel>,
    val name: String,
    val sizes: List<SizeModel>,
    val toppings: List<ToppingModel>,
    val totalFat: String
)
