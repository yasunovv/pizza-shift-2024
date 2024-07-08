package com.yasunov.cart.catalog.model


internal sealed class PizzaItemUiState {
    data object Loading : PizzaItemUiState()
    data class Success(val list: List<PizzaItemModel>) : PizzaItemUiState()
    data object Error : PizzaItemUiState()
}