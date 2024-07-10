package com.yasunov.model.entity

data class PizzaCardEntity(
    val id: Int,
    val description: String,
    val img: String,
    val ingredients: List<IngredientEntity>,
    val name: String,
    val sizes: List<SizeEntity>,
    val toppings: List<ToppingEntity>,
    val totalFat: String
)
