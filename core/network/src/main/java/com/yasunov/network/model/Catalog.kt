package com.yasunov.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Catalog(
    @SerialName("allergens") val allergens: List<String>,
    @SerialName("calories") val calories: Int,
    @SerialName("carbohydrates") val carbohydrates: String,
    @SerialName("description") val description: String,
    @SerialName("doughs") val doughs: List<Dough>,
    @SerialName("id") val id: String,
    @SerialName("img") val img: String,
    @SerialName("ingredients") val ingredients: List<Ingredient>,
    @SerialName("isGlutenFree") val isGlutenFree: Boolean,
    @SerialName("isHit") val isHit: Boolean,
    @SerialName("isNew") val isNew: Boolean,
    @SerialName("isVegetarian") val isVegetarian: Boolean,
    @SerialName("name") val name: String,
    @SerialName("protein") val protein: String,
    @SerialName("sizes") val sizes: List<Size>,
    @SerialName("sodium") val sodium: String,
    @SerialName("toppings") val toppings: List<Topping>,
    @SerialName("totalFat") val totalFat: String
)