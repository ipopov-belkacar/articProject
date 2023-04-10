package com.example.goncharov1.ui.fragment.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.goncharov1.R
import com.example.goncharov1.data.utils.DownloadImageLoader
import com.example.goncharov1.databinding.FragmentMainBinding
import com.example.goncharov1.domain.entity.ArticEntity
import com.example.goncharov1.ui.recycler.ArticListAdapter
import com.example.goncharov1.ui.recycler.RecyclerViewClickListener
import com.example.goncharov1.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@FragmentScoped
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), RecyclerViewClickListener {

    private val binding: FragmentMainBinding by viewBinding()
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var articListAdapter: ArticListAdapter

    @Inject
    lateinit var downloadImageLoader: DownloadImageLoader

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapterAndViewModel()

        lifecycleScope.launchWhenCreated {
            mainViewModel.articListFlow.collectLatest {
                articListAdapter.submitData(it)
            }
        }
    }

    private fun initAdapterAndViewModel() {
        articListAdapter = ArticListAdapter(this, downloadImageLoader)
        binding.list.adapter = articListAdapter
    }

    override fun clickItemRecycler(itemArtic: ArticEntity) {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(itemArtic.id)
        findNavController().navigate(action)
    }
}