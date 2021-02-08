package com.kilaserto.restaurantapp.ui.dishcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kilaserto.restaurantapp.db.CartEntity
import com.kilaserto.restaurantapp.repositories.DishRepository
import com.kilaserto.restaurantapp.ui.basket.BasketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DishCardViewModel(
    private val dishRepository: DishRepository,
    private val repository: BasketRepository
) : ViewModel() {

    fun getDishById(idFood: Int) = dishRepository.getDishById(idFood)

    fun plusQuantityBasketModel(idFood: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.plusUpdateBasket(idFood)
        }
    }

    fun minusQuantityBasketModel(idFood: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.minusUpdateBasket(idFood)
        }
    }

    fun deleteDishWithId(idFood: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDishById(idFood)
        }
    }

    fun addDishToBasket(cartEntity: CartEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.cartDao.insert(cartEntity)
        }
    }

    fun getDishByIdBasket(idFood: Int): LiveData<CartEntity> {
        return repository.getDishById(idFood)
    }
    fun checkFood(idFood: Int):LiveData<Boolean>{
        return repository.checkFoodBasket(idFood)
    }
}

