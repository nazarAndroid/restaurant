package com.kilaserto.restaurantapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dish_table")
data class DishModel(
    @PrimaryKey
    val id_food: Int,
    val id_category: Int,
    val title_food: String,
    val description_food: String,
    val price_food: Int,
    val volume_food: String,
    val proteins: Int,
    val fats: Int,
    val carbohydrates: Int,
    val calories: Int,
    val id_pattern_1: Int,
    val id_pattern_2: Int,
    val foto_food: String,
    val description_stop: String
)