package com.kilaserto.restaurantapp.ui.basket

import androidx.lifecycle.*
import com.kilaserto.restaurantapp.repositories.DishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketViewModel(private val repository: BasketRepository, private val dishRepository: DishRepository) : ViewModel() {

    fun deleteAllFromBasket(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBasket()
        }
    }

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
    fun deleteDishWithId(idFood: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDishById(idFood)
        }
    }

    val uiItems = repository.getCartItemsWithDish()
}