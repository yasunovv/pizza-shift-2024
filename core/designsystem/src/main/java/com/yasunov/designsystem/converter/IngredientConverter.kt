package com.yasunov.designsystem.converter

import com.yasunov.model.value.IngredientValues

fun ingredientConverter(
    name: String,
): String = when (name) {
    IngredientValues.PINEAPPLE.toString() -> "Ананас"
    IngredientValues.GREEN_PEPPER.toString() -> "Зеленый перец"
    IngredientValues.BACON.toString() -> "Бекон"
    IngredientValues.SHRIMPS.toString() -> "Креветки"
    IngredientValues.HAM.toString() -> "Ветчина"
    IngredientValues.CHICKEN_FILLET.toString() -> "Куринное филе"
    IngredientValues.ONION.toString() -> "Лук"
    IngredientValues.BASIL.toString() -> "Базилик"
    IngredientValues.CHILE.toString() -> "Чили"
    IngredientValues.CHEDDAR.toString() -> "Чеддер"
    IngredientValues.MEATBALLS.toString() -> "Мясные шарики"
    IngredientValues.PICKLE.toString() -> "Огурчик"
    IngredientValues.TOMATO.toString() -> "Томат"
    IngredientValues.FETA.toString() -> "Сыр Фета"
    IngredientValues.MOZZARELLA.toString() -> "Моцарелла"
    IngredientValues.PEPERONI.toString() -> "Пеперони"
    IngredientValues.MUSHROOMS.toString() -> "Грибы"
    IngredientValues.PARMESAN.toString() -> "Пармезан"
    else -> "Null"
}