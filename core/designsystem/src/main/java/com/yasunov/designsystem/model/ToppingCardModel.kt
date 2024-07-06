package com.yasunov.designsystem.model
import androidx.compose.runtime.Immutable
import com.yasunov.model.ToppingModel

@Immutable
data class ToppingCardModel(
    val id: Int,
    val price: Int,
    val name: String,
    val imageSrc: String
)

fun ToppingModel.asToppingCardModel(id: Int): ToppingCardModel =
    ToppingCardModel(id = id, price = price, name = name, imageSrc = img)