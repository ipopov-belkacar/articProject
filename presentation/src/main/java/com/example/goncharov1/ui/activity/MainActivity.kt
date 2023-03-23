package com.example.goncharov1.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.goncharov1.app.MainApp
import com.example.goncharov1.databinding.ActivityMainBinding
import com.example.goncharov1.ui.recycler.ArticListAdapter
import com.example.goncharov1.viewmodels.MainViewModel
import com.example.goncharov1.viewmodels.factory.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var articListAdapter: ArticListAdapter
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[MainViewModel::class.java]

        articListAdapter = ArticListAdapter()
        binding.listRecyclerView.adapter = articListAdapter

        lifecycleScope.launchWhenCreated {
            mainViewModel.getArtic.collectLatest {
                articListAdapter.submitData(it)
            }
        }
    }
}