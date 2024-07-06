package com.yasunov.model

data class PizzaCardModel(
    val id: Int,
    val description: String,
    val img: String,
    val ingredients: List<IngredientModel>,
    val name: String,
    val sizes: List<SizeModel>,
    val toppings: List<ToppingModel>,
    val totalFat: String
)
