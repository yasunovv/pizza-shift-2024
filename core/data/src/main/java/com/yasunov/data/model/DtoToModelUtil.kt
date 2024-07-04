package com.yasunov.data.model

import com.yasunov.model.PizzaModel
import com.yasunov.network.model.PizzaDTO

fun PizzaDTO.asPizzaModelList(): List<PizzaModel> {
    return catalog.map { pizzaDto->
        PizzaModel(
            id = pizzaDto.id.toInt(),
//            todo build config
            imageSrc = "https://shift-backend.onrender.com" + pizzaDto.img,
            name = pizzaDto.name,
            desc = pizzaDto.description,
            price = pizzaDto.sizes[0].price
        )

    }

}