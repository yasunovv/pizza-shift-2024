package com.yasunov.cart.ui

import androidx.lifecycle.ViewModel
import com.yasunov.cart.entity.CartItemEntity
import com.yasunov.cart.entity.CartUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(

) : ViewModel() {
    private var _uiState: MutableStateFlow<CartUiState> =
        MutableStateFlow(CartUiState.Success(list = List(6) { index ->
            CartItemEntity(
                id = index,
                imageSrc = "https://shift-backend.onrender.com/static/images/pizza/1.jpeg",
                name = "ШИФТ Суприм",
                description = "Шифт пицца с пепперони, колбасой, зеленым перцем, луком, оливками и шампиньонами",
                price = 299,
                count = 1
            )
        }, total = 6432))
    val uiState: StateFlow<CartUiState> get() = _uiState.asStateFlow()
}