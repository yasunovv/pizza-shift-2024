package com.yasunov.designsystem.converter

import com.yasunov.model.value.SizeValue

fun sizeConverter(name: String): String = when (name) {
    SizeValue.SMALL.toString() -> "Маленькая"
    SizeValue.MEDIUM.toString() -> "Средняя"
    SizeValue.LARGE.toString() -> "Большая"
    else -> "null"
}
