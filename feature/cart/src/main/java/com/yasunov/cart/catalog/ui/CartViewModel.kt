package com.yasunov.cart.catalog.ui

import androidx.lifecycle.ViewModel
import com.yasunov.common.AppDispatchers
import com.yasunov.data.PizzaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CartViewModel @Inject constructor(
    private val repository: PizzaRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {
    fun loadCart() {
//        TODO("Not yet implemented")
    }

//    private var _uiState: MutableStateFlow<PizzaItemUiState> = MutableStateFlow(PizzaItemUiState.Loading)
//    val uiState: StateFlow<PizzaItemUiState>
//        get() = _uiState.stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = PizzaItemUiState.Loading
//        )
//
//    fun loadPizzaItem() {
//        viewModelScope.launch(dispatchers.default) {
//            repository.getPizzaList()
//                .map { pizzaModelList ->
//                    pizzaModelList.map { pizzaModel ->
//                        PizzaItemModel(
//                            id = pizzaModel.id,
//                            imageSrc = pizzaModel.imageSrc,
//                            name = pizzaModel.name,
//                            description = pizzaModel.desc,
//                            price = pizzaModel.price,
//
//                            )
//                    }
//
//                }
//                .catch { _uiState.update { PizzaItemUiState.Error } }
//                .collect { pizzaModelItem ->
//                    _uiState.update {
//                        PizzaItemUiState.Success(
//                            pizzaModelItem
//                        )
//                    }
//                }
//
//        }

//    }

}