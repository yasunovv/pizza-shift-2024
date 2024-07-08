package com.yasunov.catalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasunov.catalog.entity.PizzaItemEntity
import com.yasunov.catalog.entity.PizzaItemUiState
import com.yasunov.common.AppDispatchers
import com.yasunov.data.PizzaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CatalogViewModel @Inject constructor(
    private val repository: PizzaRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private var _uiState: MutableStateFlow<PizzaItemUiState> =
        MutableStateFlow(PizzaItemUiState.Loading)
    val uiState: StateFlow<PizzaItemUiState> get() = _uiState.asStateFlow()

    fun loadPizzaItem() {
        viewModelScope.launch(dispatchers.default) {
            try {
                val list = repository.getPizzaList()
                    .map { pizzaModel ->
                        PizzaItemEntity(
                            id = pizzaModel.id,
                            imageSrc = pizzaModel.imageSrc,
                            name = pizzaModel.name,
                            description = pizzaModel.desc,
                            price = pizzaModel.price,
                        )
                    }
                _uiState.update {
                    PizzaItemUiState.Success(
                        list
                    )
                }
            } catch (e: Exception) {
                _uiState.update { PizzaItemUiState.Error }
            }

        }

    }

}