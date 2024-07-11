package com.yasunov.catalog.converter

import com.yasunov.catalog.model.PizzaItemModel
import com.yasunov.model.entity.PizzaEntity


fun asPizzaItemModel(pizzaEntity: PizzaEntity): PizzaItemModel {
    return PizzaItemModel(
        id = pizzaEntity.id,
        imageSrc = pizzaEntity.imageSrc,
        name = pizzaEntity.name,
        description = pizzaEntity.desc,
        price = pizzaEntity.price
    )
}