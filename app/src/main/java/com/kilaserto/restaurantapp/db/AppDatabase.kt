package com.kilaserto.restaurantapp.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [BasketsModel::class, CategoryModel::class, ChangesComposition::class,
        DishModel::class, CompositionDish::class], version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun basketDao(): BasketDao?
    abstract fun categoriesDao(): CategoriesDao?
    abstract fun changesCompositionDao(): ChangesCompositionDao?
    abstract fun dishDao(): DishDao?
    abstract fun compositionDao(): CompositionDao?
}