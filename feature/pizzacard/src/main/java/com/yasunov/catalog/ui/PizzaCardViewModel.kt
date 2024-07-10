package com.yasunov.catalog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasunov.catalog.entity.PizzaCard
import com.yasunov.catalog.entity.PizzaCardUiState
import com.yasunov.catalog.entity.Size
import com.yasunov.catalog.util.PizzaCardEntityConverter
import com.yasunov.common.AppDispatchers
import com.yasunov.data.PizzaRepository
import com.yasunov.designsystem.model.ToppingCardModel
import com.yasunov.designsystem.model.asToppingCardModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = PizzaCardViewModel.Factory::class)
class PizzaCardViewModel @AssistedInject constructor(
    private val pizzaRepository: PizzaRepository,
    private val dispatchers: AppDispatchers,
    private val converter: PizzaCardEntityConverter,
    @Assisted private val id: Int,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(id: Int): PizzaCardViewModel
    }

    private var _uiState: MutableStateFlow<PizzaCardUiState> =
        MutableStateFlow(PizzaCardUiState.Loading)
    val uiState: StateFlow<PizzaCardUiState> get() = _uiState.asStateFlow()

    fun loadPizzaCard() {
        if (_uiState.value is PizzaCardUiState.Success) return
        viewModelScope.launch(dispatchers.default) {
            pizzaRepository.getPizzaById(id)
                .map { pizzaCardModel ->
                    if (pizzaCardModel == null) error("Ошибка получения карточки с пиццей")
                    PizzaCard(
                        id = pizzaCardModel.id,
                        description = pizzaCardModel.description,
                        img = pizzaCardModel.img,
                        ingredients = pizzaCardModel.ingredients.map {
                            converter.asIngredient(
                                ingredientModel = it
                            )
                        },
                        name = pizzaCardModel.name,
                        sizes = pizzaCardModel.sizes.mapIndexed { id, item ->
                            converter.asSize(
                                id = id,
                                sizeModel = item
                            )
                        },
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
                                price = size.price,
                            ),
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

    fun addTopping(toppingCard: ToppingCardModel, isSelected: Boolean) {
        _uiState.update { uiStateSuccess ->
            uiStateSuccess as PizzaCardUiState.Success
            val newMap: MutableMap<String, Int> = hashMapOf()
            uiStateSuccess.addedToppings.forEach { (key, value) ->
                newMap[key] = value
            }
            if (isSelected) newMap[toppingCard.name] = toppingCard.price
            else newMap.remove(toppingCard.name)
            uiStateSuccess.copy(
                addedToppings = newMap.toMap(),
            )

        }
        updateTotal()


    }

    private fun updateTotal() {
        _uiState.update { uiStateSuccess ->
            uiStateSuccess as PizzaCardUiState.Success
            uiStateSuccess.copy(
                total = uiStateSuccess.selectedSize.price + uiStateSuccess.addedToppings.map { it.value }
                    .sum()
            )
        }
    }

    fun addPizza() {
        TODO("Not yet implemented")
//        todo sharedPreferences
    }

}