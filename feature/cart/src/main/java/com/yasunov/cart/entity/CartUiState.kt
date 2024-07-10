package com.yasunov.cart.entity

sealed class CartUiState {
    data object Loading : CartUiState()
    data class Success(
        val list: List<CartItemEntity>,
        val total: Int,
    ) : CartUiState()

    data object Error : CartUiState()
}
