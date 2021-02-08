package com.kilaserto.restaurantapp.repositories

import com.kilaserto.restaurantapp.db.CategoriesDao
import com.kilaserto.restaurantapp.db.CategoryModel
import com.kilaserto.restaurantapp.db.DishDao

class CategoryRepository(val categoriesDao: CategoriesDao) {
    suspend fun insertCategory(categoryModel: CategoryModel) {
        categoriesDao.insert(categoryModel)
    }

    fun getDishCategories() = categoriesDao.getAll()
}