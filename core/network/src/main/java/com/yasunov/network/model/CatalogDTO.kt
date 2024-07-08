package com.yasunov.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CatalogDTO(
    val allergens: List<String>,
    val calories: Int,
    val carbohydrates: String,
    val description: String,
    val doughs: List<DoughDTO>,
    val id: String,
    val img: String,
    val ingredients: List<IngredientDTO>,
    val isGlutenFree: Boolean,
    val isHit: Boolean,
    val isNew: Boolean,
    val isVegetarian: Boolean,
    val name: String,
    val protein: String,
    val sizes: List<SizeDTO>,
    val sodium: String,
    val toppings: List<ToppingDTO>,
    val totalFat: String
)




