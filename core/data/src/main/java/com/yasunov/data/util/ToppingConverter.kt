package com.yasunov.data.util

fun toppingConverter(name: String): String {
    return when (name) {
        ToppingsDTO.PINEAPPLE.toString() -> "Ананас"
        ToppingsDTO.GREEN_PEPPER.toString() -> "Зеленый перец"
        ToppingsDTO.BACON.toString() -> "Бекон"
        ToppingsDTO.SHRIMPS.toString() -> "Креветки"
        ToppingsDTO.HAM.toString() -> "Ветчина"
        ToppingsDTO.CHICKEN_FILLET.toString() -> "Куринное филе"
        ToppingsDTO.ONION.toString() -> "Лук"
        ToppingsDTO.BASIL.toString() -> "Базилик"
        ToppingsDTO.CHILE.toString() -> "Чили"
        ToppingsDTO.CHEDDAR.toString() -> "Чеддер"
        ToppingsDTO.MEATBALLS.toString() -> "Мясные шарики"
        ToppingsDTO.PICKLE.toString() -> "Огурчик"
        ToppingsDTO.TOMATO.toString() -> "Томат"
        ToppingsDTO.FETA.toString() -> "Сыр Фета"
        ToppingsDTO.MUSHROOMS.toString() -> "Грибы"
        ToppingsDTO.MOZZARELLA.toString() -> "Моцарелла"
        ToppingsDTO.PEPERONI.toString() -> "Пеперони"
        else -> "Null"
    }
}

enum class ToppingsDTO {
    PINEAPPLE,
    GREEN_PEPPER,
    BACON,
    SHRIMPS,
    HAM,
    CHICKEN_FILLET,
    ONION,
    BASIL,
    CHILE,
    CHEDDAR,
    MEATBALLS,
    PICKLE,
    TOMATO,
    FETA,
    MOZZARELLA,
    PEPERONI,
    MUSHROOMS
}