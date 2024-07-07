package com.yasunov.catalog.model


sealed class PizzaCardUiState {
    data object Loading : PizzaCardUiState()
    data class Success(
        val pizzaCard: PizzaCard,
        val selectedSize: Size,
        val addedToppings: MutableMap<String, Int> = hashMapOf(),
        val total: Int = 0,
        ) : PizzaCardUiState()
    data object Error : PizzaCardUiState()
}