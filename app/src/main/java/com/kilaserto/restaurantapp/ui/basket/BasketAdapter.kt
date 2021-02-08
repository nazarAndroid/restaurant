package com.kilaserto.restaurantapp.ui.basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kilaserto.restaurantapp.R
import com.kilaserto.restaurantapp.model.UIBasketModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_dish_basket.view.*

class BasketAdapter(val listener: BasketListener) :
    RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private var allBasketDish = ArrayList<UIBasketModel>()

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
        lateinit var basketsModel: UIBasketModel
        var countFood = 0

        fun bind(basketsModel: UIBasketModel) {
            this.basketsModel = basketsModel
            if (basketsModel.quantity >= 1) {
                countFood = basketsModel.quantity
                Picasso.get().load(basketsModel.foto_food).into(itemView.foto_dish)
                itemView.title_food.text = basketsModel.title_food
                itemView.price_food.text = basketsModel.price_food.toString()
                itemView.count_food_text.text = basketsModel.quantity.toString()
            }
        }

        init {
            itemView.plus_button.setOnClickListener {
                listener.onPlusDish(basketsModel.id_food)
                countFood += 1
            }
            itemView.minus_button.setOnClickListener {
                listener.onMinusDish(basketsModel.id_food)
                itemView.count_food_text.text = countFood.toString()

                countFood -= 1
                if (countFood == 0) {
                    listener.onDeleteDishBasket(basketsModel.id_food)
                }
            }
        }
    }

    fun setAllBasketDish(basketDishArray: ArrayList<UIBasketModel>) {
        this.allBasketDish = basketDishArray
        notifyDataSetChanged()
    }

    interface BasketListener {
        fun onPlusDish(basketsModelId: Int)
        fun onDeleteDishBasket(basketsModelId: Int)
        fun onMinusDish(basketsModelId: Int)
    }
}