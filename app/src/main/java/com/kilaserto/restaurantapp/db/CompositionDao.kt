package com.kilaserto.restaurantapp.db

import androidx.room.*

@Dao
interface CompositionDao {

    @Query("SELECT * FROM composition_table")
    fun getAll(): List<CompositionDish>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(compositionDish: CompositionDish)

    @Update
    fun update(compositionDish: CompositionDish)

    @Delete
    fun delete(compositionDish: CompositionDish)
}