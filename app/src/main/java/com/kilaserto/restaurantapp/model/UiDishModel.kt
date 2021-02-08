package com.kilaserto.restaurantapp.model

import com.kilaserto.restaurantapp.db.DishEntity

data class UiDishModel(
    val dishEntity:DishEntity,
    var quantity: Int
)