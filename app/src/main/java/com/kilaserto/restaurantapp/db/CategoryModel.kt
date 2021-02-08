package com.kilaserto.restaurantapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table")
data class CategoryModel (
    @PrimaryKey
    val id_category: Int,
    val title_category: String
)