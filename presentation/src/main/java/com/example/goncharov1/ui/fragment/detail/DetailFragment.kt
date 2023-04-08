package com.example.goncharov1.ui.fragment.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.goncharov1.R
import com.example.goncharov1.databinding.FragmentDetailBinding
import com.example.goncharov1.extensions.observe
import com.example.goncharov1.viewmodels.DetailViewModel
import com.example.goncharov1.viewmodels.Event
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

        viewModel.eventFlow.observe(viewLifecycleOwner) { event: Event ->
            when (event) {
                is Event.AttachImageToWindow -> event.requestBuilder.into(binding.mainImage)

                is Event.AttachViewToWindow -> {
                    attachViewToWindow(
                        artistDisplay = event.description,
                        title = event.title
                    )
                }

                is Event.DownloadImageFromNetwork -> {
                    event.imageId?.let {
                        viewModel.downloadImage(
                            urlImage = requireContext().getString(
                                R.string.main_url_for_upload_image, it
                            ), imagePlaceholder = R.drawable.image_placeholder
                        )
                    }
                }
            }
        }

        articId?.let {
            viewModel.getArticById(it)
        }
    }

    private fun attachViewToWindow(artistDisplay: String, title: String) {
        with(binding) {
            textArtistDisplay.text =
                getString(R.string.artist_display_template, artistDisplay)
            textTitle.text = getString(R.string.title_display_template, title)
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