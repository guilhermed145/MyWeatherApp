package com.myportfolio.myweatherapp

import android.app.Application
import com.myportfolio.myweatherapp.data.AppContainer
import com.myportfolio.myweatherapp.data.DefaultAppContainer

/**
 * This is the main Application.
 */
class MainApplication: Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}