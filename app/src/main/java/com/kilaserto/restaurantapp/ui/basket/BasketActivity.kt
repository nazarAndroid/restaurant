package com.kilaserto.restaurantapp.ui.basket

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kilaserto.restaurantapp.R
import com.kilaserto.restaurantapp.ui.home.HomeActivity
import com.kilaserto.restaurantapp.ui.payment.PaymentActivity
import kotlinx.android.synthetic.main.activity_basket.*
import kotlinx.android.synthetic.main.activity_dish_card_ingredients.back_button
import org.koin.androidx.viewmodel.ext.android.viewModel

class BasketActivity : AppCompatActivity() {

    var packCart = 0
    val viewModel: BasketViewModel by viewModel()
    lateinit var adapter: BasketAdapter
    var allQuantity = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        adapter = BasketAdapter(object :BasketAdapter.BasketListener{
            override fun onPlusDish(basketsModelId: Int) {
                viewModel.plusQuantityBasketModel(basketsModelId)
            }

            override fun onDeleteDishBasket(basketsModelId: Int) {
                viewModel.deleteDishWithId(basketsModelId)
            }

            override fun onMinusDish(basketsModelId: Int) {
                viewModel.minusQuantityBasketModel(basketsModelId)
            }
        })

        back_button.setOnClickListener {
            finish()
        }
        with_a.setOnClickListener {
            text_2.setTextColor(Color.WHITE)
            packCart = 0
            with_a.background.setTint(Color.parseColor("#FF00B050"))
            text_1.setTextColor(Color.BLACK)
            in_the_hall.background.setTint(Color.parseColor("#D0CECE"))
        }
        in_the_hall.setOnClickListener {
            text_1.setTextColor(Color.WHITE)
            packCart = 1
            in_the_hall.background.setTint(Color.parseColor("#FF00B050"))
            text_2.setTextColor(Color.BLACK)
            with_a.background.setTint(Color.parseColor("#D0CECE"))
        }
        go_to_payment.setOnClickListener {
            if(allQuantity >=1) {
                val intent = Intent(this@BasketActivity, PaymentActivity::class.java)
                startActivity(intent)
            }
        }
        recyclerView2.adapter = adapter
        viewModel.uiItems.observe(this, Observer {
            adapter.setAllBasketDish(it)
            var allPrice = 0
            allQuantity = 0
            it.forEach {
                allPrice += it.price_food*it.quantity
                allQuantity += it.quantity
            }
            sum_text.text = "Итог " + allPrice.toString() + " руб."
        })

        dump_button.setOnClickListener {
            viewModel.deleteAllFromBasket()
        }
    }

}