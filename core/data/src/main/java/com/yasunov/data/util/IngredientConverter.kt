package com.yasunov.data.util

fun ingredientConverter(name: String): String = when (name) {
    IngredientsDTO.PINEAPPLE.toString() -> "Ананас"
    IngredientsDTO.GREEN_PEPPER.toString() -> "Зеленый перец"
    IngredientsDTO.BACON.toString() -> "Бекон"
    IngredientsDTO.SHRIMPS.toString() -> "Креветки"
    IngredientsDTO.HAM.toString() -> "Ветчина"
    IngredientsDTO.CHICKEN_FILLET.toString() -> "Куринное филе"
    IngredientsDTO.ONION.toString() -> "Лук"
    IngredientsDTO.BASIL.toString() -> "Базилик"
    IngredientsDTO.CHILE.toString() -> "Чили"
    IngredientsDTO.CHEDDAR.toString() -> "Чеддер"
    IngredientsDTO.MEATBALLS.toString() -> "Мясные шарики"
    IngredientsDTO.PICKLE.toString() -> "Огурчик"
    IngredientsDTO.TOMATO.toString() -> "Томат"
    IngredientsDTO.FETA.toString() -> "Сыр Фета"
    IngredientsDTO.MOZZARELLA.toString() -> "Моцарелла"
    IngredientsDTO.PEPERONI.toString() -> "Пеперони"
    IngredientsDTO.MUSHROOMS.toString() -> "Грибы"
    IngredientsDTO.PARMESAN.toString() -> "Пармезан"
    else -> "Null"
}

enum class IngredientsDTO {
    MOZZARELLA,
    PEPERONI,
    MUSHROOMS,
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
    PARMESAN,
}