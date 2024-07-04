package com.yasunov.designsystem.model
import androidx.compose.runtime.Immutable
import com.yasunov.model.IngredientCardModel

@Immutable
data class IngredientCardModel(
    val id: Int,
    val price: Int,
    val name: String,
    val imageSrc: String
)

fun IngredientCardModel.asIngredientCardModel(): IngredientCardModel =
    IngredientCardModel(id = id, price = price, name = name, imageSrc = imageSrc)
