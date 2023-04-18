package com.example.goncharov1.ui.fragment.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.goncharov1.R
import com.example.goncharov1.data.utils.DownloadImageLoader
import com.example.goncharov1.databinding.FragmentMainBinding
import com.example.goncharov1.domain.entity.ArticEntity
import com.example.goncharov1.ui.recycler.ArticListAdapter
import com.example.goncharov1.ui.recycler.PaginationScrollListener
import com.example.goncharov1.ui.recycler.RecyclerViewClickListener
import com.example.goncharov1.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScoped
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), RecyclerViewClickListener {

    private val binding: FragmentMainBinding by viewBinding()
    private val mainViewModel: MainViewModel by viewModels()
    private var currentPage = 1

    private lateinit var articListAdapter: ArticListAdapter

    @Inject
    lateinit var downloadImageLoader: DownloadImageLoader

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapterAndViewModel()

        lifecycleScope.launch {
            mainViewModel.loadingListDataArtic.observe(viewLifecycleOwner) {
                articListAdapter.addListArtic(it)
            }
        }

        addScrollListener()
        mainViewModel.getArticList(currentPage)
    }

    private fun addScrollListener() {
        val linearLayoutManager = binding.list.layoutManager as LinearLayoutManager
        binding.list.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {

            override fun loadMoreItems() {
                currentPage += 1
                mainViewModel.getArticList(currentPage)
            }

            override fun isLoadingData(): Boolean {
                return mainViewModel.isLoadingData
            }
        })
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