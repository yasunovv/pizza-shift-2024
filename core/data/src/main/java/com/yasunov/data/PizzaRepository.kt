package com.yasunov.data

import com.yasunov.common.AppDispatchers
import com.yasunov.network.PizzaApi
import javax.inject.Inject

public class PizzaRepository @Inject constructor(
    pizzaApi: PizzaApi,
    dispatchers: AppDispatchers
) {

}