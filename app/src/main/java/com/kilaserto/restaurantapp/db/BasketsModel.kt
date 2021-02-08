package com.kilaserto.restaurantapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "basket_table")
data class BasketsModel (
    @PrimaryKey
    val id_cart: Int,
    val date_cart: String,
    val id_food: Int,
    val id_pattern_new: Int,
    var quantity_id_food: Int,
    val pack_cart: Int
)