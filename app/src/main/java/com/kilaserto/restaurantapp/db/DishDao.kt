package com.kilaserto.restaurantapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kilaserto.restaurantapp.model.DishModel


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

    @Query("SELECT * FROM  dish_table LEFT JOIN cart_table on dish_table.id_food = cart_table.id_food ORDER BY CASE WHEN id_category = :id THEN 1 ELSE 2 END")
    fun sortDishesByCategoryId(id: Int): LiveData<List<DishModel>>

    @Query("SELECT * FROM dish_table WHERE id_food = :id")
    fun getDishById(id: Int):LiveData<DishEntity>
}