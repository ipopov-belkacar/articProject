package com.example.goncharov1.ui.fragment.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.goncharov1.R
import com.example.goncharov1.databinding.FragmentDetailBinding
import com.example.goncharov1.viewmodels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

private const val ARG_PARAM_ARTIC_ID = "paramArticId"

@FragmentScoped
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels()
    private var articId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articId = it.getInt(ARG_PARAM_ARTIC_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.downloadImageLiveData.observe(viewLifecycleOwner) {
            it.into(binding.mainImage)
        }

        viewModel.getArticEntityLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                textArtistDisplay.text =
                    getString(R.string.artist_display_template, it.artistDisplay)
                textTitle.text = getString(R.string.title_display_template, it.title)
            }

            viewModel.downloadImage(
                requireContext().getString(R.string.main_url_for_upload_image, it.imageId),
                R.drawable.image_placeholder
            )
        }

        articId?.let {
            viewModel.getArticById(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            articId: Int
        ) = DetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_PARAM_ARTIC_ID, articId)
            }
        }
    }
}