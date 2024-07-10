package com.yasunov.designsystem.model
import androidx.compose.runtime.Immutable

@Immutable
data class ToppingCardModel(
    val id: Int,
    val price: Int,
    val name: String,
    val imageSrc: String
)

