package com.kilaserto.restaurantapp.ui.home

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kilaserto.restaurantapp.R
import com.kilaserto.restaurantapp.model.UiDishModel
import com.kilaserto.restaurantapp.ui.dishcard.DishDetailActivity
import com.kilaserto.restaurantapp.ui.payment.PaymentActivity
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.GrayscaleTransformation
import kotlinx.android.synthetic.main.card_dish.view.*

class DishAdapter(val listener: DishListener) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    var allDish = ArrayList<UiDishModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.card_dish, parent, false)
        return DishViewHolder(
            view, listener
        )
    }

    override fun getItemCount(): Int {
        return allDish.size
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(allDish[position])
    }

    class DishViewHolder(itemView: View, listener: DishListener) :
        RecyclerView.ViewHolder(itemView) {
        var allMoney = 0
        var countFood = 0
        lateinit var uiDishModel: UiDishModel

        fun bind(uiDishModel: UiDishModel) {
            this.uiDishModel = uiDishModel
            val dishModel = uiDishModel.dishModel
            itemView.title_food.text = dishModel.title_food
            itemView.price_food.text = "${dishModel.price_food} руб."
            itemView.volume_food.text = "${dishModel.volume_food} гр."

            if (dishModel.description_stop == "0") {
                itemView.description_stop_text.visibility = View.INVISIBLE
                Picasso.get().load(dishModel.foto_food).into(itemView.foto_dish)
                if(uiDishModel.quantity >=1){
                    itemView.buy_button.isClickable = false
                    itemView.buy_button.isLongClickable = false
                    itemView.buy_button.visibility = View.INVISIBLE
                    itemView.buy_const.visibility = View.VISIBLE
                    countFood = uiDishModel.quantity
                    itemView.count_food_text.text = uiDishModel.quantity.toString()
                }
                else{
                    itemView.isClickable = true
                    itemView.buy_button.isClickable = true
                    itemView.buy_button.visibility = View.VISIBLE
                    itemView.buy_const.visibility = View.INVISIBLE
                }
            } else {
                itemView.isClickable = false
                itemView.buy_button.isClickable = false
                itemView.description_stop_text.text = dishModel.description_stop
                itemView.description_stop_text.visibility = View.VISIBLE
                itemView.buy_button.isLongClickable = false
                itemView.buy_button.background.setTint(Color.parseColor("#D0CECE"))
                val picasso = Picasso.get()
                picasso
                    .load(dishModel.foto_food)
                    .transform(GrayscaleTransformation())
                    .into(itemView.foto_dish)
            }
        }

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DishDetailActivity::class.java)
                intent.putExtra("dish", uiDishModel.dishModel.id_food)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
            itemView.buy_button.setOnLongClickListener {
                val intent = Intent(itemView.context, PaymentActivity::class.java)
                ContextCompat.startActivity(itemView.context, intent, null)
                true
            }
            itemView.buy_button.setOnClickListener {
                listener.onClickDish(uiDishModel)
                itemView.buy_button.visibility = View.INVISIBLE
                itemView.buy_const.visibility = View.VISIBLE
                allMoney = uiDishModel.dishModel.price_food
                countFood = 1
                itemView.count_food_text.text = countFood.toString()

            }
            itemView.plus_button.setOnClickListener {
                listener.onPlusDish(uiDishModel)

                allMoney += uiDishModel.dishModel.price_food
                countFood += 1
                itemView.count_food_text.text = countFood.toString()

            }
            itemView.minus_button.setOnClickListener {
                listener.onMinusDish(uiDishModel)

                itemView.count_food_text.text = countFood.toString()

                countFood -= 1
                allMoney -= uiDishModel.dishModel.price_food
                itemView.count_food_text.text = countFood.toString()
                if (countFood == 0) {
                    itemView.buy_button.isClickable = true
                    itemView.buy_button.visibility = View.VISIBLE
                    itemView.buy_const.visibility = View.INVISIBLE
                }
            }
        }
    }

    interface DishListener {
        fun onClickDish(dishModel: UiDishModel)
        fun onPlusDish(dishModel: UiDishModel)
        fun onMinusDish(dishModel: UiDishModel)
    }


    fun setAllDishList(allArrayDish: ArrayList<UiDishModel>) {
        val diffCallback = DishDiffUtill(allArrayDish, allDish)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        allDish.clear()
        allDish.addAll(allArrayDish)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class DishDiffUtill(
        private val newList: ArrayList<UiDishModel>,
        private val oldList: ArrayList<UiDishModel>
    ) :
        DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            newList[newItemPosition] == oldList[oldItemPosition]
    }
}