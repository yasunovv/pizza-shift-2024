package com.yasunov.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yasunov.common.AppDispatchers
import com.yasunov.data.PizzaRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PizzaCardViewModel @AssistedInject constructor(
    private val pizzaRepository: PizzaRepository,
    private val dispatchers: AppDispatchers,
    @Assisted private val id: Int = 1
) {
    init {
        pizzaRepository.getPizzaById(id = id)
    }


    @Suppress("UNCHECKED_CAST")
    class Factory @AssistedInject constructor(
        private val pizzaRepository: PizzaRepository,
        private val dispatchers: AppDispatchers,
        @Assisted private val id: Int,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PizzaCardViewModel(
                pizzaRepository = pizzaRepository,
                dispatchers = dispatchers,
                id = id
            ) as T
        }

        class InnerFactory @Inject constructor(
            private val pizzaRepository: PizzaRepository,
            private val dispatchers: AppDispatchers,
        ) {
            fun create(id: Int): Factory {
                return Factory(pizzaRepository, dispatchers, id)
            }
        }
    }
}