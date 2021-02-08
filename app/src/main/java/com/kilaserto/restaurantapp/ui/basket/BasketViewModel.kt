package com.kilaserto.restaurantapp.ui.basket

import androidx.lifecycle.*
import com.kilaserto.restaurantapp.model.UIBasketModel
import com.kilaserto.restaurantapp.repositories.DishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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


    val uiItems: LiveData<ArrayList<UIBasketModel>> = Transformations.switchMap(repository.getBasketList()) {
        val resultLiveData = MutableLiveData<ArrayList<UIBasketModel>>()
        GlobalScope.launch(Dispatchers.IO) {
            val localCache = arrayListOf<UIBasketModel>()

            localCache.addAll(it.map {
                val dish = dishRepository.getDishByIdBlocking(it.id_food)
                val uiModel = UIBasketModel(
                    dish.id_food,
                    dish.title_food,
                    dish.price_food,
                    dish.foto_food,
                    it.quantity_id_food
                )
                uiModel
            })
            resultLiveData.postValue(localCache)
        }
        resultLiveData
    }

}