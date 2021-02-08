package com.kilaserto.restaurantapp.db

import androidx.room.*

@Dao
interface ChangesCompositionDao {

    @Query("SELECT * FROM changes_composition_table")
    fun getAll(): List<ChangesComposition>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(changesComposition: ChangesComposition)

    @Update
    fun update(changesComposition: ChangesComposition)

    @Delete
    fun delete(changesComposition: ChangesComposition)
}