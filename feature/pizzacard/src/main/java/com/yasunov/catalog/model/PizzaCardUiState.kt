package com.yasunov.catalog.model


sealed class PizzaCardUiState {
    data object Loading : PizzaCardUiState()
    data object Initial : PizzaCardUiState()
    data class Success(
        val pizzaCardModel: PizzaCardModel,
        val selectedSizeModel: SizeModel,
        val addedToppings: Map<String, Int> = hashMapOf(),
        val total: Int = selectedSizeModel.price + addedToppings.map { it.value }
            .sum(),

        ) : PizzaCardUiState()

    data object Error : PizzaCardUiState()
}