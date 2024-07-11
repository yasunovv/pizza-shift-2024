package com.yasunov.data

import com.yasunov.data.conventer.ConverterPizzaCardDto
import com.yasunov.data.conventer.ConverterPizzaModelDto
import com.yasunov.model.entity.PizzaCardEntity
import com.yasunov.model.entity.PizzaEntity
import com.yasunov.network.PizzaApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PizzaRepository @Inject constructor(
    private val api: PizzaApi,
    private val converterPizzaCardDto: ConverterPizzaCardDto,
    private val converterPizzaModelDto: ConverterPizzaModelDto,
) {

    suspend fun <T : Any> getPizzaList(converter: (PizzaEntity) -> T): List<T> {
        return api.getCatalog().catalog.map {
            converterPizzaModelDto.asPizzaModel(it)
        }.map { converter(it) }
    }

    fun <T : Any> getPizzaById(id: Int, converter: (PizzaCardEntity) -> T): Flow<T?> {
        return flow {
            val item = api.getCatalog().catalog.find { it.id.toInt() == id }
            if (item == null) {
                emit(null)
                return@flow
            }
            emit(converter(converterPizzaCardDto.asPizzaCardModel(item)))
        }
    }
}
