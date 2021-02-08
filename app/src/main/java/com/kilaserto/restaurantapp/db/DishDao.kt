package com.kilaserto.restaurantapp.db

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DishDao {

    @Query("SELECT * FROM dish_table")
    fun getAll(): LiveData<List<DishModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dishModel: DishModel)

    @Update
    fun update(dishModel: DishModel)

    @Delete
    fun delete(dishModel: DishModel)

    @Query("SELECT * FROM dish_table ORDER BY CASE WHEN id_category = :id THEN 1 ELSE 2 END")
    fun sortDishesByCategoryId(id: Int): LiveData<List<DishModel>>

    @Query("SELECT * FROM dish_table WHERE id_food = :id")
    fun getDishById(id: Int):LiveData<DishModel>

    @Query("SELECT * FROM dish_table WHERE id_food = :id")
    fun getDishByIdBlocking(id: Int):DishModel
}