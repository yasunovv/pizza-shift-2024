package com.yasunov.data.conventer


fun sizeConverter(name: String): String = when (name) {
    SizeDTO.SMALL.toString() -> "Маленькая"
    SizeDTO.MEDIUM.toString() -> "Средняя"
    SizeDTO.LARGE.toString() -> "Большая"
    else -> "null"
}

enum class SizeDTO {
    SMALL, MEDIUM, LARGE
}