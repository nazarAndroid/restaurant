package com.kilaserto.restaurantapp

import androidx.room.Room
import com.kilaserto.restaurantapp.db.AppDatabase
import com.kilaserto.restaurantapp.repositories.CategoryRepository
import com.kilaserto.restaurantapp.repositories.DishRepository
import com.kilaserto.restaurantapp.ui.basket.BasketRepository
import com.kilaserto.restaurantapp.ui.basket.BasketViewModel
import com.kilaserto.restaurantapp.ui.dishcard.DishCardViewModel
import com.kilaserto.restaurantapp.ui.home.HomeViewModel
import com.kilaserto.restaurantapp.ui.payment.PaymentViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "database").build()
    }
    single { get<AppDatabase>().dishDao() }
    single { get<AppDatabase>().categoriesDao() }
    single { get<AppDatabase>().basketDao() }
    single { get<AppDatabase>().changesCompositionDao() }
    single { get<AppDatabase>().compositionDao() }

    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { BasketViewModel(get(), get()) }
    viewModel { DishCardViewModel(get(), get()) }
    viewModel { PaymentViewModel() }

    factory { BasketRepository(get()) }
    factory { DishRepository(get()) }
    factory { CategoryRepository(get()) }
}