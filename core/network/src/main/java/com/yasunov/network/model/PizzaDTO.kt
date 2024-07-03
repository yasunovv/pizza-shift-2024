package com.yasunov.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzaDTO(
     val catalog: List<CatalogDTO>,
     val success: Boolean
)