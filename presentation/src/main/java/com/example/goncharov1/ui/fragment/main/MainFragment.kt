package com.example.goncharov1.ui.fragment.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.goncharov1.R
import com.example.goncharov1.data.utils.DownloadImageLoader
import com.example.goncharov1.databinding.FragmentMainBinding
import com.example.goncharov1.domain.entity.ArticEntity
import com.example.goncharov1.ui.base.BaseFragment
import com.example.goncharov1.ui.recycler.ArticListAdapter
import com.example.goncharov1.ui.recycler.RecyclerViewClickListener
import com.example.goncharov1.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@FragmentScoped
@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main), RecyclerViewClickListener {

    private val binding: FragmentMainBinding by viewBinding()
    override val viewModel: MainViewModel by viewModels()

    private lateinit var articListAdapter: ArticListAdapter

    @Inject
    lateinit var downloadImageLoader: DownloadImageLoader

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapterAndViewModel()

        lifecycleScope.launchWhenCreated {
            viewModel.articListFlow().collectLatest {
                articListAdapter.submitData(it)
            }
        }

        binding.btnGoProfile.setOnClickListener {
            MainFragmentDirections.actionMainFragmentToProfileFragment().navigate()
        }
    }

    private fun initAdapterAndViewModel() {
        articListAdapter = ArticListAdapter(this, downloadImageLoader)
        binding.list.adapter = articListAdapter
    }

    override fun clickItemRecycler(itemArtic: ArticEntity) {
        MainFragmentDirections.actionMainFragmentToDetailFragment(itemArtic.id).navigate()
    }
}