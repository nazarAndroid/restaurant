package com.kilaserto.restaurantapp.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [CartEntity::class, CategoryModel::class, ChangesComposition::class,
        DishEntity::class, CompositionDish::class], version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun basketDao(): CartDao?
    abstract fun categoriesDao(): CategoriesDao?
    abstract fun changesCompositionDao(): ChangesCompositionDao?
    abstract fun dishDao(): DishDao?
    abstract fun compositionDao(): CompositionDao?
}