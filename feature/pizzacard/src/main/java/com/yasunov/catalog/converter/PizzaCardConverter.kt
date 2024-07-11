package com.yasunov.catalog.converter

import com.yasunov.catalog.model.IngredientModel
import com.yasunov.catalog.model.PizzaCardModel
import com.yasunov.catalog.model.SizeModel
import com.yasunov.designsystem.converter.ingredientConverter
import com.yasunov.designsystem.converter.sizeConverter
import com.yasunov.designsystem.converter.toppingConverter
import com.yasunov.designsystem.model.ToppingCardModel
import com.yasunov.model.entity.IngredientEntity
import com.yasunov.model.entity.PizzaCardEntity
import com.yasunov.model.entity.SizeEntity
import com.yasunov.model.entity.ToppingEntity

class PizzaCardConverter {

    private fun asIngredient(ingredientEntity: IngredientEntity): IngredientModel =
        IngredientModel(
            cost = ingredientEntity.cost,
            img = ingredientEntity.img,
            name = ingredientConverter(ingredientEntity.name)
        )

    private fun asSize(sizeEntity: SizeEntity, id: Int): SizeModel =
        SizeModel(id = id, name = sizeConverter(sizeEntity.name), price = sizeEntity.price)

    fun asPizzaCardModel(pizzaCardEntity: PizzaCardEntity): PizzaCardModel = PizzaCardModel(
        id = pizzaCardEntity.id,
        description = pizzaCardEntity.description,
        img = pizzaCardEntity.img,
        ingredientModels = pizzaCardEntity.ingredients.map {
            asIngredient(
                ingredientEntity = it
            )
        },
        name = pizzaCardEntity.name,
        sizeModels = pizzaCardEntity.sizes.mapIndexed { id, item ->
            asSize(
                id = id,
                sizeEntity = item
            )
        },
        toppings = pizzaCardEntity.toppings.mapIndexed { index, item ->
            asToppingCardModel(id = index, toppingEntity = item)
        }
    )

    private fun asToppingCardModel(id: Int, toppingEntity: ToppingEntity): ToppingCardModel =
        ToppingCardModel(
            id = id,
            price = toppingEntity.price,
            name = toppingConverter(toppingEntity.name),
            imageSrc = toppingEntity.img
        )


}