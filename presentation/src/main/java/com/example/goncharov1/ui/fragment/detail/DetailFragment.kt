package com.example.goncharov1.ui.fragment.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.goncharov1.R
import com.example.goncharov1.data.utils.DownloadImageLoader
import com.example.goncharov1.databinding.FragmentDetailBinding
import com.example.goncharov1.domain.entity.ArticEntity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

private const val ARG_PARAM_ARTIC_ITEM = "paramArticItem"

@FragmentScoped
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private var articItem: ArticEntity? = null

    @Inject
    lateinit var downloadImageLoader: DownloadImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articItem = Gson().fromJson(it.getString(ARG_PARAM_ARTIC_ITEM), ArticEntity::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            textArtistDisplay.text =
                getString(R.string.artist_display_template, articItem?.artistDisplay)
            textTitle.text = getString(R.string.title_display_template, articItem?.title)
        }
        uploadImage()
    }

    private fun uploadImage() {
        articItem?.imageId.let {
            downloadImageLoader.downloadImage(
                requireContext().getString(R.string.main_url_for_upload_image, it),
                R.drawable.image_placeholder,
            ).into(binding.mainImage)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            itemArtic: String
        ) = DetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM_ARTIC_ITEM, itemArtic)
            }
        }
    }
}