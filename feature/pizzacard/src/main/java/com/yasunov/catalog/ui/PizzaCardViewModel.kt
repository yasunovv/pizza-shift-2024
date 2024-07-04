package com.yasunov.catalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.yasunov.catalog.model.PizzaCard
import com.yasunov.catalog.model.PizzaCardUiState
import com.yasunov.catalog.util.asIngredient
import com.yasunov.catalog.util.asSize
import com.yasunov.catalog.util.asTopping
import com.yasunov.common.AppDispatchers
import com.yasunov.data.PizzaRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = PizzaCardViewModel.Factory::class)
class PizzaCardViewModel @AssistedInject constructor(
    private val pizzaRepository: PizzaRepository,
    private val dispatchers: AppDispatchers,
    @Assisted private val id: Int
) : ViewModel() {
    private var _uiState: MutableStateFlow<PizzaCardUiState> =
        MutableStateFlow(PizzaCardUiState.Loading)
    val uiState: StateFlow<PizzaCardUiState>
        get() = _uiState.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PizzaCardUiState.Loading
        )

    init {
        loadPizzaCard()
    }

    fun loadPizzaCard() {
        viewModelScope.launch(dispatchers.default) {
            pizzaRepository.getPizzaById(id)
                .map { pizzaCardModel ->
                    if (pizzaCardModel == null) throw IllegalStateException()
                    PizzaCard(
                        id = pizzaCardModel.id,
                        description = pizzaCardModel.description,
                        img = pizzaCardModel.img,
                        ingredients = pizzaCardModel.ingredients.map { it.asIngredient() },
                        name = pizzaCardModel.name,
                        sizes = pizzaCardModel.sizes.map { it.asSize() },
                        toppings = pizzaCardModel.toppings.map { it.asTopping() }

                    )
                }
                .catch { _uiState.update { PizzaCardUiState.Error } }
                .collect { pizzaCard ->
                    _uiState.update {
                        PizzaCardUiState.Success(
                            pizzaCard = pizzaCard
                        )
                    }
                }


        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int): PizzaCardViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return assistedFactory.create(id = id) as T
            }

        }
    }
}