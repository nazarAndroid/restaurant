package com.kilaserto.restaurantapp.ui.basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kilaserto.restaurantapp.R
import com.kilaserto.restaurantapp.model.CartWithDishItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_dish_basket.view.*

class BasketAdapter(private val listener: BasketListener) :
    RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private lateinit var allBasketDish: List<CartWithDishItem>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.card_dish_basket, parent, false)
        return BasketViewHolder(
            view, listener
        )
    }

    override fun getItemCount(): Int {
        return allBasketDish.size
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(allBasketDish[position])
    }

    class BasketViewHolder(itemView: View, listener: BasketListener) :
        RecyclerView.ViewHolder(itemView) {
        var foodId: Int = -1
        var countFood = 0

        fun bind(cartItem: CartWithDishItem) {
            this.foodId = cartItem.dishEntity.id_food
            if (cartItem.quantity() >= 1) {
                countFood = cartItem.quantity()
                Picasso.get().load(cartItem.dishEntity.foto_food).into(itemView.foto_dish)
                itemView.title_food.text = cartItem.dishEntity.title_food
                itemView.price_food.text = cartItem.dishEntity.price_food.toString()
                itemView.count_food_text.text = cartItem.quantity().toString()
            }
        }

        init {
            itemView.plus_button.setOnClickListener {
                listener.onPlusDish(foodId)
                countFood += 1
            }
            itemView.minus_button.setOnClickListener {
                listener.onMinusDish(foodId)
                itemView.count_food_text.text = countFood.toString()

                countFood -= 1
                if (countFood == 0) {
                    listener.onDeleteDishBasket(foodId)
                }
            }
        }
    }

    fun setAllBasketDish(basketDishArray: List<CartWithDishItem>) {
        this.allBasketDish = basketDishArray
        notifyDataSetChanged()
    }

    interface BasketListener {
        fun onPlusDish(basketsModelId: Int)
        fun onDeleteDishBasket(basketsModelId: Int)
        fun onMinusDish(basketsModelId: Int)
    }
}