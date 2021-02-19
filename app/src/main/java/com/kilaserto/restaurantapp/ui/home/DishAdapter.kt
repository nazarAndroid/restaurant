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
import com.kilaserto.restaurantapp.model.DishModel
import com.kilaserto.restaurantapp.ui.dishcard.DishDetailActivity
import com.kilaserto.restaurantapp.ui.payment.PaymentActivity
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.GrayscaleTransformation
import kotlinx.android.synthetic.main.card_dish.view.*

class DishAdapter(val listener: DishListener) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    var allDish = ArrayList<DishModel>()

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

    inner class DishViewHolder(itemView: View, listener: DishListener) :
        RecyclerView.ViewHolder(itemView) {
        var allMoney = 0
        var countFood = 0

        fun bind(uiDishModel: DishModel) {
            itemView.title_food.text = uiDishModel.title_food
            itemView.price_food.text = "${uiDishModel.price_food} руб."
            itemView.volume_food.text = "${uiDishModel.volume_food} гр."

            if (uiDishModel.description_stop == "0") {
                itemView.description_stop_text.visibility = View.INVISIBLE
                Picasso.get().load(uiDishModel.foto_food).into(itemView.foto_dish)
                countFood = uiDishModel.quantity_id_food
                if (uiDishModel.quantity_id_food >= 1) {
                    itemView.buy_button.isClickable = false
                    itemView.buy_button.isLongClickable = false
                    itemView.buy_button.visibility = View.INVISIBLE
                    itemView.buy_const.visibility = View.VISIBLE
                    itemView.count_food_text.text = uiDishModel.quantity_id_food.toString()
                } else {
                    itemView.isClickable = true
                    itemView.buy_button.isClickable = true
                    itemView.buy_button.visibility = View.VISIBLE
                    itemView.buy_const.visibility = View.INVISIBLE
                }
            } else {
                itemView.isClickable = false
                itemView.buy_button.isClickable = false
                itemView.description_stop_text.text = uiDishModel.description_stop
                itemView.description_stop_text.visibility = View.VISIBLE
                itemView.buy_button.isLongClickable = false
                itemView.buy_button.background.setTint(Color.parseColor("#D0CECE"))
                val picasso = Picasso.get()
                picasso
                    .load(uiDishModel.foto_food)
                    .transform(GrayscaleTransformation())
                    .into(itemView.foto_dish)
            }
        }

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DishDetailActivity::class.java)
                intent.putExtra("dish", getDish(adapterPosition).id_food)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
            itemView.buy_button.setOnLongClickListener {
                val intent = Intent(itemView.context, PaymentActivity::class.java)
                ContextCompat.startActivity(itemView.context, intent, null)
                true
            }
            itemView.buy_button.setOnClickListener {
                listener.onClickDish(getDish(adapterPosition))
                itemView.buy_button.visibility = View.INVISIBLE
                itemView.buy_const.visibility = View.VISIBLE
                allMoney = getDish(adapterPosition).price_food
                countFood = 1
                itemView.count_food_text.text = countFood.toString()

            }
            itemView.plus_button.setOnClickListener {
                listener.onPlusDish(getDish(adapterPosition))

                allMoney += getDish(adapterPosition).price_food
                countFood += 1
                itemView.count_food_text.text = countFood.toString()

            }
            itemView.minus_button.setOnClickListener {
                listener.onMinusDish(getDish(adapterPosition))

                itemView.count_food_text.text = countFood.toString()

                countFood -= 1
                allMoney -= getDish(adapterPosition).price_food
                itemView.count_food_text.text = countFood.toString()
                if (countFood == 0) {
                    itemView.buy_button.isClickable = true
                    itemView.buy_button.visibility = View.VISIBLE
                    itemView.buy_const.visibility = View.INVISIBLE
                }
            }
        }
    }

    fun getDish(position: Int) = allDish[position]


    interface DishListener {
        fun onClickDish(dishModel: DishModel)
        fun onPlusDish(dishModel: DishModel)
        fun onMinusDish(dishModel: DishModel)
    }


    fun setAllDishList(allArrayDish: ArrayList<DishModel>) {
        val diffCallback = DishDiffUtill(allArrayDish, allDish)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        allDish.clear()
        allDish.addAll(allArrayDish)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class DishDiffUtill(
        private val newList: ArrayList<DishModel>,
        private val oldList: ArrayList<DishModel>
    ) :
        DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            newList[newItemPosition] == oldList[oldItemPosition]
    }
}