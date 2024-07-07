package com.yasunov.catalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasunov.catalog.model.PizzaCard
import com.yasunov.catalog.model.PizzaCardUiState
import com.yasunov.catalog.model.Size
import com.yasunov.catalog.util.asIngredient
import com.yasunov.catalog.util.asSize
import com.yasunov.common.AppDispatchers
import com.yasunov.data.PizzaRepository
import com.yasunov.designsystem.model.ToppingCardModel
import com.yasunov.designsystem.model.asToppingCardModel
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
                        sizes = pizzaCardModel.sizes.mapIndexed { id, item -> item.asSize(id = id) },
                        toppings = pizzaCardModel.toppings.mapIndexed { index, item ->
                            item.asToppingCardModel(
                                index
                            )
                        }

                    )
                }
                .catch { _uiState.update { PizzaCardUiState.Error } }
                .collect { pizzaCard ->
                    _uiState.update {
                        val size = pizzaCard.sizes[0]
                        PizzaCardUiState.Success(
                            pizzaCard = pizzaCard,
                            selectedSize = Size(
                                id = size.id,
                                name = size.name,
                                price = size.price
                            ),
                            total = pizzaCard.sizes[0].price
                        )
                    }
                }
        }
    }

    fun selectPizza(id: Int) {
        _uiState.update {
            val size = (it as PizzaCardUiState.Success).pizzaCard.sizes[id]
            it.copy(
                selectedSize = Size(
                    id = size.id,
                    name = size.name,
                    price = size.price
                )
            )
        }
        updateTotal()
    }

    private fun updateTotal() {
        _uiState.update {
            (it as PizzaCardUiState.Success).copy(
                total = it.selectedSize.price +
                        it.addedToppings.values.sum()
            )
        }
    }

    fun addTopping(toppingCard: ToppingCardModel, isAdd: Boolean) {
        _uiState.update { uiStateSuccess ->
            uiStateSuccess as PizzaCardUiState.Success
            val newMap = uiStateSuccess.addedToppings
            if (isAdd) newMap[toppingCard.name] = toppingCard.price
            else newMap.remove(toppingCard.name)
            uiStateSuccess.copy(
                addedToppings = newMap
            )
        }
        updateTotal()
    }

    fun addPizza() {
        TODO("Not yet implemented")
//        todo sharedPreferences
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int): PizzaCardViewModel
    }
}