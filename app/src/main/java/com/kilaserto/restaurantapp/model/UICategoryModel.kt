package com.kilaserto.restaurantapp.model

data class UICategoryModel(
    val id_category: Int,
    val title_category: String,
    var isSelected: Boolean = false
)