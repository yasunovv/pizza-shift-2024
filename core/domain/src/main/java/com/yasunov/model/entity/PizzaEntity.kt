package com.yasunov.model.entity

data class PizzaEntity(
    val id: Int,
    val imageSrc: String,
    val name: String,
    val desc: String,
    val price: Int,
)