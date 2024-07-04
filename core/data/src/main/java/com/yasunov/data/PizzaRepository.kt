package com.yasunov.data

import com.yasunov.common.AppDispatchers
import com.yasunov.data.model.PizzaExtendedModel
import com.yasunov.data.util.asPizzaExtendedModel
import com.yasunov.data.util.asPizzaModel
import com.yasunov.model.PizzaModel
import com.yasunov.network.PizzaApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PizzaRepository @Inject constructor(
    private val api: PizzaApi,
    private val dispatchers: AppDispatchers
) {
    fun getPizzaList(): Flow<List<PizzaModel>> {
        return flow {
            emit(api.getCatalog().catalog.map { it.asPizzaModel() })
        }
            .flowOn(dispatchers.io)
    }

    fun getPizzaById(id: Int): Flow<PizzaExtendedModel?> {
        return flow {
            emit((api.getCatalog().catalog.find { it.id.toInt() == id })?.asPizzaExtendedModel())
        }
            .flowOn(dispatchers.io)
    }

}
