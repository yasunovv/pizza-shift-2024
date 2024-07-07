package com.yasunov.catalog.util

import com.yasunov.catalog.model.Ingredient
import com.yasunov.catalog.model.Size
import com.yasunov.model.IngredientModel
import com.yasunov.model.SizeModel

fun IngredientModel.asIngredient(): Ingredient = Ingredient(cost = cost, img = img, name = name)

fun SizeModel.asSize(id: Int): Size = Size(id = id, name = name, price = price)
