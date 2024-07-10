package com.yasunov.data.conventer

import com.yasunov.model.entity.PizzaEntity
import com.yasunov.network.model.CatalogDTO
import javax.inject.Inject
import javax.inject.Named

class ConverterPizzaModelDto @Inject constructor(
    @Named("BASE_URL")
    private val baseUrl: String,
) {
    fun asPizzaModel(catalogDTO: CatalogDTO): PizzaEntity = PizzaEntity(
        id = catalogDTO.id.toInt(),
        imageSrc = baseUrl + catalogDTO.img,
        name = catalogDTO.name,
        desc = catalogDTO.description,
        price = catalogDTO.sizes[0].price
    )
}