package com.kilaserto.restaurantapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "composition_table")
data class CompositionDish (
    @PrimaryKey
    val id_pattern: Int,
    val name_pattern: String,
    val foto_ingredient:String,
    val id_ingredient: Int,
    val name_ingredient: String,
    val vol_ingredient: String,
    val price_ingredient: Int,
    val box_ingredient: Int
)
