package com.yasunov.data.conventer

import com.yasunov.model.entity.IngredientEntity
import com.yasunov.model.entity.PizzaCardEntity
import com.yasunov.model.entity.SizeEntity
import com.yasunov.model.entity.ToppingEntity
import com.yasunov.network.model.CatalogDTO
import com.yasunov.network.model.IngredientDTO
import com.yasunov.network.model.SizeDTO
import com.yasunov.network.model.ToppingDTO
import javax.inject.Inject
import javax.inject.Named

class ConverterPizzaCardDto @Inject constructor(
    @Named("BASE_URL")
    private val baseUrl: String,

    ) {
    fun asPizzaCardModel(catalogDTO: CatalogDTO): PizzaCardEntity {
        return PizzaCardEntity(
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

    private fun asIngredientsModelList(ingredientDTO: IngredientDTO): IngredientEntity =
        IngredientEntity(
            cost = ingredientDTO.cost,
            img = baseUrl + ingredientDTO.img,
            name = ingredientDTO.name
        )

    private fun asSizeModel(sizeDTO: SizeDTO): SizeEntity = SizeEntity(
        name = sizeDTO.name, price = sizeDTO.price
    )

    private fun asToppingModel(toppingDTO: ToppingDTO): ToppingEntity = ToppingEntity(
        price = toppingDTO.cost,
        img = baseUrl + toppingDTO.img,
        name = toppingDTO.name
    )
}
