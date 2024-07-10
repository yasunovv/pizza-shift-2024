package com.yasunov.catalog.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasunov.catalog.converter.PizzaCardConverter
import com.yasunov.catalog.model.PizzaCardUiState
import com.yasunov.catalog.model.SizeModel
import com.yasunov.common.AppDispatchers
import com.yasunov.data.PizzaRepository
import com.yasunov.designsystem.model.ToppingCardModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = PizzaCardViewModel.Factory::class)
class PizzaCardViewModel @AssistedInject constructor(
    private val pizzaRepository: PizzaRepository,
    private val dispatchers: AppDispatchers,
    private val converter: PizzaCardConverter,
    @Assisted private val id: Int,
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(id: Int): PizzaCardViewModel
    }

    private var _uiState: MutableStateFlow<PizzaCardUiState> =
        MutableStateFlow(PizzaCardUiState.Initial)
    val uiState: StateFlow<PizzaCardUiState> get() = _uiState.asStateFlow()

    fun loadPizzaCard() {
        if (_uiState.value is PizzaCardUiState.Success || _uiState.value is PizzaCardUiState.Loading) return
        _uiState.update { PizzaCardUiState.Loading }
        val exceptionHandler = CoroutineExceptionHandler { _, _ ->
            Log.d(TAG, "Coroutine loadPizzaCard was cancelled")
            _uiState.update { PizzaCardUiState.Error }
        }
        viewModelScope.launch(dispatchers.default + exceptionHandler) {
            pizzaRepository.getPizzaById(id = id, converter = { converter.asPizzaCardModel(it) })
                .catch {
                    _uiState.update { PizzaCardUiState.Error }
                }
                .collect { pizzaCard ->
                    if (pizzaCard == null) error("Ошибка получения карточки с пиццей")
                    _uiState.update {
                        val size = pizzaCard.sizeModels[0]
                        PizzaCardUiState.Success(
                            pizzaCardModel = pizzaCard,
                            selectedSizeModel = SizeModel(
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
            val size = (it as PizzaCardUiState.Success).pizzaCardModel.sizeModels[id]
            it.copy(
                selectedSizeModel = SizeModel(
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
                total = uiStateSuccess.selectedSizeModel.price + uiStateSuccess.addedToppings.map { it.value }
                    .sum()
            )
        }
    }

    fun addPizza() {
        TODO("Not yet implemented")
//        todo sharedPreferences
    }

    fun checkSelectedTopping(name: String): Boolean {
        return name in (uiState.value as PizzaCardUiState.Success).addedToppings

    }

    companion object {
        private const val TAG = "PizzaCardViewModel"
    }

}