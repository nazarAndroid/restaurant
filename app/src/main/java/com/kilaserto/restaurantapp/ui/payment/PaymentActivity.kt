package com.kilaserto.restaurantapp.ui.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kilaserto.restaurantapp.R
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        back_button.setOnClickListener {
            finish()
        }
    }
}