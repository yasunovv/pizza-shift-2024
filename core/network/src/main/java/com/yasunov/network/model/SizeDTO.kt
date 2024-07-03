package com.yasunov.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SizeDTO(
     val name: String,
     val price: Int
)