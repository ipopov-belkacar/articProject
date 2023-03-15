package com.example.goncharov1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.goncharov1.app.MainApp
import com.example.goncharov1.R
import com.example.goncharov1.di.AppComponent
import com.example.goncharov1.domain.getArtic.GetArticUseCaseImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var articUseCaseImpl: GetArticUseCaseImpl

    lateinit var daggerAppComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daggerAppComponent = (applicationContext as MainApp).appComponent
        daggerAppComponent.injectMainActivity(this)

        GlobalScope.launch {
            println("!!!!!!!" + articUseCaseImpl.getArtic())
        }
    }
}