package com.yasunov.catalog.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasunov.catalog.converter.asPizzaItemModel
import com.yasunov.catalog.model.PizzaItemUiState
import com.yasunov.common.AppDispatchers
import com.yasunov.data.PizzaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CatalogViewModel @Inject constructor(
    private val repository: PizzaRepository,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private var _uiState: MutableStateFlow<PizzaItemUiState> =
        MutableStateFlow(PizzaItemUiState.Initial)
    val uiState: StateFlow<PizzaItemUiState> get() = _uiState.asStateFlow()

    fun loadPizzaItem() {
        if (_uiState.value is PizzaItemUiState.Loading || _uiState.value is PizzaItemUiState.Success) return
        _uiState.update { PizzaItemUiState.Loading }
        val exceptionHandler = CoroutineExceptionHandler { _, _ ->
            Log.d(TAG, "Coroutine loadPizzaCard was cancelled")
            _uiState.update { PizzaItemUiState.Error }
        }
        viewModelScope.launch(dispatchers.default + exceptionHandler) {
            try {
                val list = repository.getPizzaList(::asPizzaItemModel)
                _uiState.update {
                    PizzaItemUiState.Success(list = list)
                }
            } catch (e: Exception) {
                _uiState.update { PizzaItemUiState.Error }
            }

        }

    }

    companion object {
        private const val TAG = "CatalogViewModel"
    }

}