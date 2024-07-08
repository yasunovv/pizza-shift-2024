package com.yasunov.catalog.util

import com.yasunov.catalog.entity.Ingredient
import com.yasunov.catalog.entity.Size
import com.yasunov.model.IngredientModel
import com.yasunov.model.SizeModel

class PizzaCardEntityConverter {
    fun asIngredient(ingredientModel: IngredientModel): Ingredient =
        Ingredient(
            cost = ingredientModel.cost,
            img = ingredientModel.img,
            name = ingredientModel.name
        )

    fun asSize(sizeModel: SizeModel, id: Int): Size =
        Size(id = id, name = sizeModel.name, price = sizeModel.price)
}