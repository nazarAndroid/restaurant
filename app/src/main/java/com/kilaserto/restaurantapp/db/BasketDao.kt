package com.kilaserto.restaurantapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket_table")
    fun getAll(): LiveData<List<BasketsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(basketsModel: BasketsModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<BasketsModel>)

    @Update
    fun update(basketsModel: BasketsModel)

    @Delete
    fun delete(basketsModel: BasketsModel)

    @Query("DELETE FROM basket_table")
    fun deleteAll()

    @Query("SELECT * FROM basket_table WHERE id_food= :id")
    fun getDishByIdBasket(id: Int): LiveData<BasketsModel>

    @Query("SELECT * FROM basket_table WHERE id_food= :id")
    fun getDishByIdBasketBlocking(id: Int): BasketsModel?

    @Query("SELECT * FROM basket_table WHERE id_food= :id")
    fun getDishById(id: Int): BasketsModel

    @Query("DELETE FROM basket_table WHERE id_food= :id")
    fun deleteDishById(id: Int)

    @Query("SELECT EXISTS (SELECT 1 FROM basket_table WHERE id_food = :id)")
    fun checkFoodOnTable(id: Int): LiveData<Boolean>
}