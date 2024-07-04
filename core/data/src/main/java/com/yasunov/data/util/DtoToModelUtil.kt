package com.yasunov.data.util

import com.yasunov.data.model.PizzaExtendedModel
import com.yasunov.model.IngredientModel
import com.yasunov.model.PizzaModel
import com.yasunov.model.SizeModel
import com.yasunov.model.ToppingModel
import com.yasunov.network.model.CatalogDTO
import com.yasunov.network.model.IngredientDTO
import com.yasunov.network.model.PizzaDTO
import com.yasunov.network.model.SizeDTO
import com.yasunov.network.model.ToppingDTO

fun CatalogDTO.asPizzaModel(): PizzaModel = PizzaModel(
    id = id.toInt(), imageSrc = img, name = name, desc = description, price = sizes[0].price
)

fun CatalogDTO.asPizzaExtendedModel(): PizzaExtendedModel {
    return PizzaExtendedModel(
        id = id.toInt(),
        description = description,
        img = img,
        ingredients = ingredients.map { it.asIngredientsModelList() },
        name = name,
        sizes = sizes.map { it.asSizeModel() },
        toppings = toppings.map { it.asToppingModel() },
        totalFat = totalFat
    )
}


fun IngredientDTO.asIngredientsModelList(): IngredientModel = IngredientModel(
    cost = cost, img = img, name = name
)

fun SizeDTO.asSizeModel(): SizeModel = SizeModel(
    name = name, price = price
)

fun ToppingDTO.asToppingModel(): ToppingModel = ToppingModel(
    cost = cost, img = img, name = name

)