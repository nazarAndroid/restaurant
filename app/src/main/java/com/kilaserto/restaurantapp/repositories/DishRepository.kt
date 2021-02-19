package com.kilaserto.restaurantapp.repositories

import com.kilaserto.restaurantapp.db.DishDao

class DishRepository(val dishDao: DishDao) {
    fun getDishById(id: Int) = dishDao.getDishById(id)
}