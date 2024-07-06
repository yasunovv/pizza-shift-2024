package com.yasunov.catalog.model


public sealed class PizzaCardUiState {
    data object Loading : PizzaCardUiState()
    data class Success(
        val pizzaCard: PizzaCard,
        val sizePrice: Int? = null,
        val addedToppings: MutableMap<String, Int> = hashMapOf(),
        val total: Int = 0,

        ) : PizzaCardUiState()
    data object Error : PizzaCardUiState()
}