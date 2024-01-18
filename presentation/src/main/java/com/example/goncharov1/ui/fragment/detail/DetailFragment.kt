package com.example.goncharov1.ui.fragment.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.goncharov1.R
import com.example.goncharov1.databinding.FragmentDetailBinding
import com.example.goncharov1.extensions.observe
import com.example.goncharov1.ui.base.BaseFragment
import com.example.goncharov1.viewmodels.DetailViewModel
import com.example.goncharov1.viewmodels.Event
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
@AndroidEntryPoint
class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding()
    override val viewModel: DetailViewModel by viewModels()

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

        viewModel.getArticById()

        binding.toolbar.setNavigationOnClickListener {
            goBack()
        }
    }

    private fun attachViewToWindow(artistDisplay: String, title: String) {
        with(binding) {
            textArtistDisplay.text =
                getString(R.string.artist_display_template, artistDisplay)
            textTitle.text = getString(R.string.title_display_template, title)
        }
    }
}