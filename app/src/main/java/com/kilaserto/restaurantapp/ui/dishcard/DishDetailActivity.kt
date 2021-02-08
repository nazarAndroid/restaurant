package com.kilaserto.restaurantapp.ui.dishcard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kilaserto.restaurantapp.R
import com.kilaserto.restaurantapp.db.BasketsModel
import com.kilaserto.restaurantapp.db.DishModel
import com.kilaserto.restaurantapp.ui.basket.BasketActivity
import com.kilaserto.restaurantapp.ui.home.HomeActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dish_card_ingredients.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class DishDetailActivity : AppCompatActivity() {
    var idFood = 0
    lateinit var food: DishModel
    var countFood = 1
    private val viewModel: DishCardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_card_ingredients)
        val arguments = intent.extras
        idFood = arguments!!["dish"].hashCode()
        viewModel.getDishById(idFood).observe(this, Observer {
            food = it
            val currentTime: Date = Calendar.getInstance().time
            val dish =
                BasketsModel(
                    food.id_food,
                    currentTime.toString(),
                    food.id_food,
                    0,
                    countFood,
                    0
                )
            viewModel.addDishToBasket(dish)

            title_food.text = food.title_food
            volume_food.text = food.volume_food + " гр."
            Picasso.get().load(food.foto_food).into(foto_dish)
            proteins_text.text = food.proteins.toString() + " г"
            fats_text.text = food.fats.toString() + " г"
            carbohydrates_text.text = food.carbohydrates.toString() + " г"
            calories_text.text = food.calories.toString() + " ккал"
            price_food.text = food.price_food.toString() + "руб."

            val dish1 = viewModel.getDishByIdBasket(food.id_food)
            dish1.observe(this, Observer {
                countFood = it.quantity_id_food
                count_food_text.text = countFood.toString()
            })

        })


        plus_button.setOnClickListener {
            countFood += 1
            viewModel.plusQuantityBasketModel(food.id_food)
            count_food_text.text = countFood.toString()
        }
        minus_button.setOnClickListener {
            if (countFood > 1) {
                countFood -= 1
                viewModel.minusQuantityBasketModel(food.id_food)
                count_food_text.text = countFood.toString()
            } else {
                viewModel.deleteDishWithId(food.id_food)
                val intent = Intent(this@DishDetailActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        buy_button.setOnClickListener {
            val intent = Intent(this@DishDetailActivity, BasketActivity::class.java)
            startActivity(intent)
            finish()
        }
        back_button.setOnClickListener {
            finish()
        }
        sliding_layout.isTouchEnabled = false
        info_button.setOnClickListener {
            sliding_layout.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
            sliding_layout.isTouchEnabled = true
        }
    }
}