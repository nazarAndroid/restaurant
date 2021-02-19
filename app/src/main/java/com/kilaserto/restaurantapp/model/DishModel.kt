package com.kilaserto.restaurantapp.model

data class DishModel(
    val id_food: Int,
    val id_category: Int,
    val title_food: String,
    val description_food: String,
    val price_food: Int,
    val volume_food: String,
    val proteins: Int,
    val fats: Int,
    val carbohydrates: Int,
    val calories: Int,
    val id_pattern_1: Int,
    val id_pattern_2: Int,
    val foto_food: String,
    val description_stop: String,
    val quantity_id_food: Int
){
    fun cost() = quantity_id_food*price_food
}