package com.yasunov.data

import com.yasunov.data.conventer.ConverterDto
import com.yasunov.model.PizzaCardModel
import com.yasunov.model.PizzaModel
import com.yasunov.network.PizzaApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PizzaRepository @Inject constructor(
    private val api: PizzaApi,
    private val converterDto: ConverterDto,
) {
    suspend fun getPizzaList(): List<PizzaModel> {
        return api.getCatalog().catalog.map {
            converterDto.asPizzaModel(it)
        }
    }
//    todo исправить
    fun getPizzaById(id: Int): Flow<PizzaCardModel?> {
        return flow {
            val item = api.getCatalog().catalog.find { it.id.toInt() == id }
            if (item == null) {
                emit(null)
                return@flow
            }
            emit(converterDto.asPizzaCardModel(item))

        }
    }
}
