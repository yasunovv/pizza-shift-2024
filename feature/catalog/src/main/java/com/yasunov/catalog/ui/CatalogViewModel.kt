package com.yasunov.catalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasunov.catalog.model.PizzaItemModel
import com.yasunov.catalog.model.UiState
import com.yasunov.common.AppDispatchers
import com.yasunov.data.PizzaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CatalogViewModel @Inject constructor(
    repository: PizzaRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {
    private var _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState>
        get() = _uiState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )

    init {
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
                .collect { pizzaModelItem ->
                    _uiState.update {
                        UiState.Success(
                            pizzaModelItem
                        )
                    }
                }
                .runCatching { _uiState.update { UiState.Error } }
        }
    }
}