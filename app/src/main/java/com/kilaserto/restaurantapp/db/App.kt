package com.kilaserto.restaurantapp.db

import android.app.Application
import androidx.room.Room
import com.kilaserto.restaurantapp.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {
    var database: AppDatabase? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .build()
        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }

    companion object {
        var instance: App? = null
    }
}