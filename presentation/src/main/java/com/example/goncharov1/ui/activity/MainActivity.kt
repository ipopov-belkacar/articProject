package com.example.goncharov1.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goncharov1.databinding.ActivityMainBinding
import com.example.goncharov1.ui.recycler.ArticListAdapter
import com.example.goncharov1.viewmodels.MainViewModel
import com.example.goncharov1.viewmodels.MainViewModel_Factory
import com.example.goncharov1.viewmodels.factory.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var articListAdapter: ArticListAdapter
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        articListAdapter = ArticListAdapter()
        binding.list.adapter = articListAdapter

        mainViewModel = ViewModelProvider(
            this,
            viewmodelFactory
        )[MainViewModel::class.java]

        mainViewModel.getArticLiveData.observe(this) {
            articListAdapter.updateListArtic(it)
        }

        mainViewModel.getArtic()
    }
}