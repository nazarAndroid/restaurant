package com.kilaserto.restaurantapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kilaserto.restaurantapp.model.CartWithDishItem

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_table")
    fun getAll(): LiveData<List<CartEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cartEntity: CartEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<CartEntity>)

    @Update
    fun update(cartEntity: CartEntity)

    @Delete
    fun delete(cartEntity: CartEntity)

    @Query("DELETE FROM cart_table")
    fun deleteAll()

    @Query("SELECT * FROM cart_table WHERE id_food= :id")
    fun getDishByIdBasket(id: Int): LiveData<CartEntity>

    @Query("SELECT * FROM cart_table WHERE id_food= :id")
    fun getDishById(id: Int): CartEntity

    @Query("DELETE FROM cart_table WHERE id_food= :id")
    fun deleteDishById(id: Int)

    @Query("SELECT EXISTS (SELECT 1 FROM cart_table WHERE id_food = :id)")
    fun checkFoodOnTable(id: Int): LiveData<Boolean>

    @Transaction @Query("select * from cart_table")
    fun getCartItemsWithDish(): LiveData<List<CartWithDishItem>>
}