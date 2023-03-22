package com.example.goncharov1.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.goncharov1.databinding.FragmentMainBinding
import com.example.goncharov1.ui.recycler.ArticListAdapter
import com.example.goncharov1.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@FragmentScoped
@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var articListAdapter: ArticListAdapter
    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapterAndViewModel()

        lifecycleScope.launchWhenCreated {
            mainViewModel.getArtic.collectLatest {
                articListAdapter.submitData(it)
            }
        }
    }

    private fun initAdapterAndViewModel() {
        articListAdapter = ArticListAdapter()
        binding.list.adapter = articListAdapter

        mainViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[MainViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}