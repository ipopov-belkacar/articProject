package com.example.goncharov1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.goncharov1.R
import com.example.goncharov1.di.AppComponent
import com.example.goncharov1.di.DaggerAppComponent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appComponent: AppComponent = DaggerAppComponent.create()
        val articUseCaseImpl = appComponent.getArticUseCase()

        GlobalScope.launch {
            println("!!!!!!!" + articUseCaseImpl.getArtic())
        }
    }
}