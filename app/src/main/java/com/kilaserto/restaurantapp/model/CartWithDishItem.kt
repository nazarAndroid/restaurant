package com.kilaserto.restaurantapp.model

import androidx.room.Embedded
import androidx.room.Relation
import com.kilaserto.restaurantapp.db.CartEntity
import com.kilaserto.restaurantapp.db.DishEntity

data class CartWithDishItem(
    @Embedded val cart: CartEntity,
    @Relation(
        parentColumn = "id_food",
        entityColumn = "id_food"
    )
    val dishEntity: DishEntity
) {
    fun quantity() = cart.quantity_id_food

    fun cost() = cart.quantity_id_food * dishEntity.price_food
}