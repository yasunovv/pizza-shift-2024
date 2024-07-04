package com.yasunov.catalog.model


public sealed class PizzaCardUiState {
    data object Loading : PizzaCardUiState()
    data class Success(val pizzaCard: PizzaCard) : PizzaCardUiState()
    data object Error : PizzaCardUiState()
}