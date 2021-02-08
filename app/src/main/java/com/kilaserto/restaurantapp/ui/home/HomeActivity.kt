package com.kilaserto.restaurantapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kilaserto.restaurantapp.R
import com.kilaserto.restaurantapp.db.BasketsModel
import com.kilaserto.restaurantapp.db.DishModel
import com.kilaserto.restaurantapp.model.UiDishModel
import com.kilaserto.restaurantapp.ui.basket.BasketActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class HomeActivity : AppCompatActivity() {
    lateinit var adapter: CategoriesAdapter
    lateinit var adapterDish: DishAdapter

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapterDish = DishAdapter(object : DishAdapter.DishListener{
            override fun onClickDish(uiDishModel: UiDishModel) {
                val currentTime: Date = Calendar.getInstance().time
                val dish = BasketsModel(uiDishModel.dishModel.id_food,currentTime.toString(),uiDishModel.dishModel.id_food,0,1,0 )
                viewModel.addDishToBasket(dish)
            }

            override fun onPlusDish(uiDishModel: UiDishModel) {
                viewModel.plusQuantityBasketModel(uiDishModel.dishModel.id_food)
            }

            override fun onMinusDish(uiDishModel: UiDishModel) {
                viewModel.minusQuantityBasketModel(uiDishModel.dishModel.id_food)
            }
        })

        observeData()
        initViews()
        buy_card_view.setOnClickListener {
            val intent = Intent(this@HomeActivity, BasketActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initViews() {
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recycler_categories.layoutManager = mLayoutManager

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        adapter = CategoriesAdapter(object :
            CategoriesAdapter.CategoriesListener {
            override fun onCategoriSelected(position: Int) {
                viewModel.onCategoryChanged(position)
            }
        })

        recycler_dish.adapter = adapterDish
        recycler_categories.adapter = adapter
    }

    private fun observeData() {
        viewModel.mainDishes().observe(this, Observer {
            adapterDish.setAllDishList(it)
        })

        viewModel.mainCategories().observe(this, Observer {
            adapter.setAllCategories(it)
        })

        viewModel.mainDishesList.observe(this, Observer {
            if (it.isNotEmpty()) {
                buy_card_view.visibility = View.VISIBLE
                var allPrice = 0
                it.forEach {
                    allPrice += it.dishModel.price_food*it.quantity
                }
                if(allPrice == 0){
                    buy_card_view.visibility = View.INVISIBLE
                }
                price_all_food.text = allPrice.toString()
            } else {
                buy_card_view.visibility = View.INVISIBLE
            }
        })
    }
}