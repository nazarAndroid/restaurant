package com.kilaserto.restaurantapp.repositories

import com.kilaserto.restaurantapp.db.DishDao

class DishRepository(val dishDao: DishDao) {
    fun getDishesForCategory(position: Int) = dishDao.sortDishesByCategoryId(position)

    fun getDishById(id: Int) = dishDao.getDishById(id)

    fun getDishByIdBlocking(id: Int) = dishDao.getDishByIdBlocking(id)
}