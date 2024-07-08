package com.yasunov.catalog.entity


internal sealed class PizzaItemUiState {
    data object Loading : PizzaItemUiState()
    data class Success(val list: List<PizzaItemEntity>) : PizzaItemUiState()
    data object Error : PizzaItemUiState()
}