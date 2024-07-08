package com.yasunov.data.conventer

import com.yasunov.model.IngredientModel
import com.yasunov.model.PizzaCardModel
import com.yasunov.model.PizzaModel
import com.yasunov.model.SizeModel
import com.yasunov.model.ToppingModel
import com.yasunov.network.model.CatalogDTO
import com.yasunov.network.model.IngredientDTO
import com.yasunov.network.model.SizeDTO
import com.yasunov.network.model.ToppingDTO
import javax.inject.Inject
import javax.inject.Named

class ConverterDto @Inject constructor(
    @Named("BASE_URL")
    private val baseUrl: String,
) {
    internal fun asPizzaModel(catalogDTO: CatalogDTO): PizzaModel = PizzaModel(
        id = catalogDTO.id.toInt(),
        imageSrc = baseUrl + catalogDTO.img,
        name = catalogDTO.name,
        desc = catalogDTO.description,
        price = catalogDTO.sizes[0].price
    )

    fun asPizzaCardModel(catalogDTO: CatalogDTO): PizzaCardModel {
        return PizzaCardModel(
            id = catalogDTO.id.toInt(),
            description = catalogDTO.description,
            img = baseUrl + catalogDTO.img,
            ingredients = catalogDTO.ingredients.map { asIngredientsModelList(it) },
            name = catalogDTO.name,
            sizes = catalogDTO.sizes.map { asSizeModel(it) },
            toppings = catalogDTO.toppings.map { asToppingModel(it) },
            totalFat = catalogDTO.totalFat
        )
    }

    fun asIngredientsModelList(ingredientDTO: IngredientDTO): IngredientModel =
        IngredientModel(
            cost = ingredientDTO.cost,
            img = baseUrl + ingredientDTO.img,
            name = ingredientConverter(ingredientDTO.name)
        )

    fun asSizeModel(sizeDTO: SizeDTO): SizeModel = SizeModel(
        name = sizeConverter(sizeDTO.name), price = sizeDTO.price
    )

    fun asToppingModel(toppingDTO: ToppingDTO): ToppingModel = ToppingModel(
        price = toppingDTO.cost,
        img = baseUrl + toppingDTO.img,
        name = toppingConverter(toppingDTO.name)
    )
}
