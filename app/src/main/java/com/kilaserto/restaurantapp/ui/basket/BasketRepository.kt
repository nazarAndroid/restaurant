package com.kilaserto.restaurantapp.ui.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kilaserto.restaurantapp.db.CartDao
import com.kilaserto.restaurantapp.db.CartEntity

class BasketRepository(val cartDao: CartDao) {
    fun getBasketList() = Transformations.switchMap(cartDao.getAll()) {
        MutableLiveData<List<CartEntity>>(it)
    }

    fun deleteBasket() {
        cartDao.deleteAll()
    }

    fun plusUpdateBasket(idFood: Int) {
        val basketDish = cartDao.getDishById(idFood)
        basketDish.quantity_id_food += 1
        cartDao.update(basketDish)
    }

    fun minusUpdateBasket(idFood: Int) {
        val basketDish = cartDao.getDishById(idFood)
        basketDish.quantity_id_food -= 1
        cartDao.update(basketDish)
    }

    fun deleteDishById(idFood: Int) {
        cartDao.deleteDishById(idFood)
    }

    fun getDishById(idFood: Int) =
        cartDao.getDishByIdBasket(idFood)

    fun checkFoodBasket(idFood: Int) = cartDao.checkFoodOnTable(idFood)

    fun getCartItemsWithDish() = cartDao.getCartItemsWithDish()
}