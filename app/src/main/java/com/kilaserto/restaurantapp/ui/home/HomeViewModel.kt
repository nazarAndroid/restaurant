package com.kilaserto.restaurantapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kilaserto.restaurantapp.db.CartEntity
import com.kilaserto.restaurantapp.db.CategoryModel
import com.kilaserto.restaurantapp.db.DishEntity
import com.kilaserto.restaurantapp.model.DishModel
import com.kilaserto.restaurantapp.model.UICategoryModel
import com.kilaserto.restaurantapp.repositories.CategoryRepository
import com.kilaserto.restaurantapp.repositories.DishRepository
import com.kilaserto.restaurantapp.ui.basket.BasketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val cartRepository: BasketRepository,
    private val dishRepository: DishRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    val mainDishesList: MediatorLiveData<ArrayList<DishModel>> = MediatorLiveData()

    private var dishCategories = MediatorLiveData<ArrayList<UICategoryModel>>()
    private var localCategories: ArrayList<UICategoryModel> = arrayListOf()

    fun plusQuantityBasketModel(idFood: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.plusUpdateBasket(idFood)
        }
    }

    fun minusQuantityBasketModel(idFood: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.minusUpdateBasket(idFood)
        }
    }

    init {
        val categoriesSource = categoryRepository.getDishCategories()
        dishCategories.addSource(categoriesSource) {
            dishCategories.removeSource(categoriesSource)
            localCategories.clear()
            it.forEach {
                localCategories.add(UICategoryModel(it.id_category, it.title_category))
            }
            dishCategories.value = localCategories

            onCategoryChanged(0)
        }

        viewModelScope.launch(Dispatchers.IO) {
            fillTestData()
        }

    }

    fun addDishToBasket(cartEntity: CartEntity) {

        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.cartDao.insert(cartEntity)
        }
    }

    fun mainDishes() = mainDishesList

    fun mainCategories() = dishCategories

    var source: LiveData<List<DishModel>>? = null
    fun onCategoryChanged(position: Int) {
        Log.d("tag", "onCategory changed $position")
        source?.let {
            mainDishesList.removeSource(it)
        }
        source = dishRepository.dishDao.sortDishesByCategoryId(position)
        mainDishesList.addSource(source!!) {
            mainDishesList.value = ArrayList(it)
        }
        localCategories.forEach {
            it.isSelected = false
        }
        localCategories.find { it.id_category == position }?.let {
            it.isSelected = true
        }
        dishCategories.value = localCategories
    }

    fun fillTestData() {
        val cat1 = CategoryModel(0, "Первое")
        val cat2 = CategoryModel(1, "Второе")
        val cat3 = CategoryModel(2, "Третье")
        val cat4 = CategoryModel(3, "Четверте")


        categoryRepository.categoriesDao.insert(cat1)
        categoryRepository.categoriesDao.insert(cat2)
        categoryRepository.categoriesDao.insert(cat3)
        categoryRepository.categoriesDao.insert(cat4)

        val dish1 = DishEntity(
            0,
            1,
            "Борщ",
            "ням ням",
            300,
            450.toString(),
            10,
            15,
            20,
            30,
            1,
            1,
            "https://bipbap.ru/wp-content/uploads/2017/06/14813uxVsXjAPBFmIovEzZkHNnR.jpg",
            "Скоро будет!"
        )
        dishRepository.dishDao.insert(dish1)
        val dish2 = DishEntity(
            1,
            2,
            "Суп",
            "ням ням",
            300,
            450.toString(),
            10,
            15,
            20,
            30,
            1,
            1,
            "https://bipbap.ru/wp-content/uploads/2017/06/14813uxVsXjAPBFmIovEzZkHNnR.jpg",
            "0"
        )
        dishRepository.dishDao.insert(dish2)
        val dish3 = DishEntity(
            2,
            3,
            "Пюре",
            "ням ням",
            300,
            450.toString(),
            10,
            15,
            20,
            30,
            1,
            1,
            "https://bipbap.ru/wp-content/uploads/2017/06/14813uxVsXjAPBFmIovEzZkHNnR.jpg",
            "Скоро будет"
        )
        dishRepository.dishDao.insert(dish3)
        val dish4 = DishEntity(
            3,
            4,
            "Кальмар",
            "ням ням",
            300,
            450.toString(),
            10,
            15,
            20,
            30,
            1,
            1,
            "https://bipbap.ru/wp-content/uploads/2017/06/14813uxVsXjAPBFmIovEzZkHNnR.jpg",
            "0"
        )
        dishRepository.dishDao.insert(dish4)
        val dish5 = DishEntity(
            4,
            5,
            "Test",
            "ням ням",
            300,
            450.toString(),
            10,
            15,
            20,
            30,
            1,
            1,
            "https://bipbap.ru/wp-content/uploads/2017/06/14813uxVsXjAPBFmIovEzZkHNnR.jpg",
            "0"
        )
        dishRepository.dishDao.insert(dish5)
    }
}
