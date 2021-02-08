package com.kilaserto.restaurantapp.ui.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kilaserto.restaurantapp.db.BasketDao
import com.kilaserto.restaurantapp.db.BasketsModel

class BasketRepository(val basketDao: BasketDao) {
    fun getBasketList() = Transformations.switchMap(basketDao.getAll()) {
        MutableLiveData<List<BasketsModel>>(it)
    }

    fun deleteBasket() {
        basketDao.deleteAll()
    }

    fun plusUpdateBasket(idFood: Int) {
        val basketDish = basketDao.getDishById(idFood)
        basketDish.quantity_id_food += 1
        basketDao.update(basketDish)
    }

    fun minusUpdateBasket(idFood: Int) {
        val basketDish = basketDao.getDishById(idFood)
        basketDish.quantity_id_food -= 1
        basketDao.update(basketDish)
    }

    fun deleteDishById(idFood: Int) {
        basketDao.deleteDishById(idFood)
    }

    fun getDishById(idFood: Int) =
        basketDao.getDishByIdBasket(idFood)

    fun checkFoodBasket(idFood: Int) = basketDao.checkFoodOnTable(idFood)
}