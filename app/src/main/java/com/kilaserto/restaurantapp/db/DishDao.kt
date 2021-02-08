package com.kilaserto.restaurantapp.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DishDao {

    @Query("SELECT * FROM dish_table")
    fun getAll(): LiveData<List<DishEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dishEntity: DishEntity)

    @Update
    fun update(dishEntity: DishEntity)

    @Delete
    fun delete(dishEntity: DishEntity)

    @Query("SELECT * FROM dish_table ORDER BY CASE WHEN id_category = :id THEN 1 ELSE 2 END")
    fun sortDishesByCategoryId(id: Int): LiveData<List<DishEntity>>

    @Query("SELECT * FROM dish_table WHERE id_food = :id")
    fun getDishById(id: Int):LiveData<DishEntity>

    @Query("SELECT * FROM dish_table WHERE id_food = :id")
    fun getDishByIdBlocking(id: Int):DishEntity
}