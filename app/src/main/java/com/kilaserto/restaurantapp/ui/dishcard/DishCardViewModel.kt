package com.kilaserto.restaurantapp.ui.dishcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kilaserto.restaurantapp.db.BasketsModel
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

    fun addDishToBasket(basketsModel: BasketsModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.basketDao.insert(basketsModel)
        }
    }

    fun getDishByIdBasket(idFood: Int): LiveData<BasketsModel> {
        return repository.getDishById(idFood)
    }
    fun checkFood(idFood: Int):LiveData<Boolean>{
        return repository.checkFoodBasket(idFood)
    }
}

