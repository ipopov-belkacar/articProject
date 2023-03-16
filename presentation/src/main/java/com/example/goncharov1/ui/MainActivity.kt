package com.example.goncharov1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.goncharov1.R
import com.example.goncharov1.app.MainApp
import com.example.goncharov1.di.AppComponent
import com.example.goncharov1.viewmodels.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var daggerAppComponent: AppComponent
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daggerAppComponent = (applicationContext as MainApp).appComponent
        daggerAppComponent.injectMainActivity(this)

        mainViewModel = ViewModelProvider(
            this,
            daggerAppComponent.injectViewModelFactory()
        )[MainViewModel::class.java]

        mainViewModel.getArticLiveData.observe(this) {
            println(it)
        }

        GlobalScope.launch {
            mainViewModel.getArtic()
        }
    }
}