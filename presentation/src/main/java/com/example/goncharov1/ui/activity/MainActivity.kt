package com.example.goncharov1.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.goncharov1.app.MainApp
import com.example.goncharov1.databinding.ActivityMainBinding
import com.example.goncharov1.di.AppComponent
import com.example.goncharov1.ui.recycler.ArticListAdapter
import com.example.goncharov1.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private lateinit var daggerAppComponent: AppComponent
    private lateinit var mainViewModel: MainViewModel
    private lateinit var articListAdapter: ArticListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        daggerAppComponent = (applicationContext as MainApp).appComponent
        daggerAppComponent.injectMainActivity(this)

        mainViewModel = ViewModelProvider(
            this,
            daggerAppComponent.injectViewModelFactory()
        )[MainViewModel::class.java]

        articListAdapter = ArticListAdapter()
        binding.list.adapter = articListAdapter

        lifecycleScope.launchWhenCreated {
            mainViewModel.getArtic.collectLatest {
                articListAdapter.submitData(it)
            }
        }
    }
}