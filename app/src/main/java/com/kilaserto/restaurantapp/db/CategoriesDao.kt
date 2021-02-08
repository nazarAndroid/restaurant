package com.kilaserto.restaurantapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM categories_table")
    fun getAll(): LiveData<List<CategoryModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryModel: CategoryModel)

    @Update
    fun update(categoryModel: CategoryModel)

    @Delete
    fun delete(categoryModel: CategoryModel)
}