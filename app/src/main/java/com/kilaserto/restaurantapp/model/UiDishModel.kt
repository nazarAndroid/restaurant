package com.kilaserto.restaurantapp.model

import com.kilaserto.restaurantapp.db.DishModel

data class UiDishModel(
    val dishModel:DishModel,
    var quantity: Int
)