package com.yasunov.catalog.entity


sealed class PizzaCardUiState {
    data object Loading : PizzaCardUiState()
    data class Success(
        val pizzaCard: PizzaCard,
        val selectedSize: Size,
        val addedToppings: Map<String, Int> = hashMapOf(),
        val total: Int = selectedSize.price + addedToppings.map { it.value }
            .sum(),

        ) : PizzaCardUiState()

    data object Error : PizzaCardUiState()
}