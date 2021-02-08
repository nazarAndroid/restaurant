package com.kilaserto.restaurantapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "changes_composition_table")
data class ChangesComposition(
    @PrimaryKey
    val id_pattern_new: Int,
    val id_ingredient: Int,
    val volume_select: Float
)