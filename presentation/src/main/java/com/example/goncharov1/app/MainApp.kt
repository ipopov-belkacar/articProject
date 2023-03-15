package com.example.goncharov1.app

import android.app.Application
import com.example.goncharov1.di.AppComponent
import com.example.goncharov1.di.DaggerAppComponent

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}