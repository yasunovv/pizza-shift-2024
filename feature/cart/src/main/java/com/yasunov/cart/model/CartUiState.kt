package com.yasunov.cart.model

sealed class CartUiState {
    data object Loading : CartUiState()
    data class Success(
        val list: List<CartItemModel>,
        val total: Int,
    ) : CartUiState()

    data object Error : CartUiState()
}
