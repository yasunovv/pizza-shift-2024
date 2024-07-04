package com.yasunov.catalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasunov.catalog.model.PizzaItemModel
import com.yasunov.catalog.model.PizzaItemUiState
import com.yasunov.common.AppDispatchers
import com.yasunov.data.PizzaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CatalogViewModel @Inject constructor(
    private val repository: PizzaRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private var _uiState: MutableStateFlow<PizzaItemUiState> = MutableStateFlow(PizzaItemUiState.Loading)
    val uiState: StateFlow<PizzaItemUiState>
        get() = _uiState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PizzaItemUiState.Loading
        )

    init {
        loadPizzaItem()
    }

    fun loadPizzaItem() {
        viewModelScope.launch(dispatchers.default) {
            repository.getPizzaList()
                .map { pizzaModelList ->
                    pizzaModelList.map { pizzaModel ->
                        PizzaItemModel(
                            id = pizzaModel.id,
                            imageSrc = pizzaModel.imageSrc,
                            name = pizzaModel.name,
                            description = pizzaModel.desc,
                            price = pizzaModel.price,

                            )
                    }

                }
                .catch { _uiState.update { PizzaItemUiState.Error } }
                .collect { pizzaModelItem ->
                    _uiState.update {
                        PizzaItemUiState.Success(
                            pizzaModelItem
                        )
                    }
                }

        }

    }

}