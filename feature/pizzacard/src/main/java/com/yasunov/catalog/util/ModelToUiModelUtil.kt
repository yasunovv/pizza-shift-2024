package com.yasunov.catalog.util

import com.yasunov.catalog.model.Ingredient
import com.yasunov.catalog.model.Size
import com.yasunov.catalog.model.Topping
import com.yasunov.model.IngredientModel
import com.yasunov.model.SizeModel
import com.yasunov.model.ToppingModel

fun IngredientModel.asIngredient(): Ingredient = Ingredient(cost = cost, img = img, name = name)

fun SizeModel.asSize(): Size = Size(name = name, price = price)

fun ToppingModel.asTopping(id: Int): Topping = Topping(id = id, cost = price, img = img, name = name)