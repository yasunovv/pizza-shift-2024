package com.yasunov.data.model

import com.yasunov.model.PizzaModel
import com.yasunov.network.model.PizzaDTO

fun PizzaDTO.asPizzaModelList(): List<PizzaModel> {
    val pizzaList: MutableList<PizzaModel> = mutableListOf()
    catalog.forEach { pizzaDto ->
        pizzaList.add(
            PizzaModel(
                imageSrc = pizzaDto.imgSrc,
                name = pizzaDto.name,
                desc = pizzaDto.description,
                price = pizzaDto.sizes[0].price
            )
        )
    }
    return pizzaList

}