package com.yasunov.data.util

import com.yasunov.model.IngredientModel
import com.yasunov.model.PizzaCardModel
import com.yasunov.model.PizzaModel
import com.yasunov.model.SizeModel
import com.yasunov.model.ToppingModel
import com.yasunov.network.model.CatalogDTO
import com.yasunov.network.model.IngredientDTO
import com.yasunov.network.model.SizeDTO
import com.yasunov.network.model.ToppingDTO

private const val BASE_URL = "https://shift-backend.onrender.com"

fun CatalogDTO.asPizzaModel(): PizzaModel = PizzaModel(
    id = id.toInt(), imageSrc = BASE_URL + img, name = name, desc = description, price = sizes[0].price
)

fun CatalogDTO.asPizzaCardModel(): PizzaCardModel {
    return PizzaCardModel(
        id = id.toInt(),
        description = description,
        img = BASE_URL+img,
        ingredients = ingredients.map { it.asIngredientsModelList() },
        name = name,
        sizes = sizes.map { it.asSizeModel() },
        toppings = toppings.map { it.asToppingModel() },
        totalFat = totalFat
    )
}


fun IngredientDTO.asIngredientsModelList(): IngredientModel = IngredientModel(
    cost = cost, img = BASE_URL + img, name = name
)

fun SizeDTO.asSizeModel(): SizeModel = SizeModel(
    name = name, price = price
)

fun ToppingDTO.asToppingModel(): ToppingModel = ToppingModel(
    price = cost, img = BASE_URL + img, name = name

)