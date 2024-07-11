package com.yasunov.designsystem.converter

import com.yasunov.model.value.ToppingValue

fun toppingConverter(name: String): String {
    return when (name) {
        ToppingValue.PINEAPPLE.toString() -> "Ананас"
        ToppingValue.GREEN_PEPPER.toString() -> "Зеленый перец"
        ToppingValue.BACON.toString() -> "Бекон"
        ToppingValue.SHRIMPS.toString() -> "Креветки"
        ToppingValue.HAM.toString() -> "Ветчина"
        ToppingValue.CHICKEN_FILLET.toString() -> "Куринное филе"
        ToppingValue.ONION.toString() -> "Лук"
        ToppingValue.BASIL.toString() -> "Базилик"
        ToppingValue.CHILE.toString() -> "Чили"
        ToppingValue.CHEDDAR.toString() -> "Чеддер"
        ToppingValue.MEATBALLS.toString() -> "Мясные шарики"
        ToppingValue.PICKLE.toString() -> "Огурчик"
        ToppingValue.TOMATO.toString() -> "Томат"
        ToppingValue.FETA.toString() -> "Сыр Фета"
        ToppingValue.MUSHROOMS.toString() -> "Грибы"
        ToppingValue.MOZZARELLA.toString() -> "Моцарелла"
        ToppingValue.PEPERONI.toString() -> "Пеперони"
        else -> "Null"
    }
}

