package com.kilaserto.restaurantapp.db

import android.app.Application
import com.kilaserto.restaurantapp.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }
}