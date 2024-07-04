package com.yasunov.catalog.model


internal sealed class UiState {
    data object Loading : UiState()
    data class Success(val list: List<PizzaItemModel>) : UiState()
    data object Error : UiState()
}