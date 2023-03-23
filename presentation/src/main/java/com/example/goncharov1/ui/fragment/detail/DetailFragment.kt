package com.example.goncharov1.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.goncharov1.R
import com.example.goncharov1.databinding.FragmentDetailBinding

private const val ARG_PARAM_TITLE = "paramTitle"
private const val ARG_PARAM_ARTIST = "paramArtist"
private const val ARG_PARAM_IMAGE_ID = "paramImageId"

class DetailFragment : Fragment() {

    private var paramTitle: String? = null
    private var paramArtisDisplay: String? = null
    private var paramImageId: String? = null

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramTitle = it.getString(ARG_PARAM_TITLE)
            paramArtisDisplay = it.getString(ARG_PARAM_ARTIST)
            paramImageId = it.getString(ARG_PARAM_IMAGE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            textArtistDisplay.text = paramTitle
            textTitle.text = paramArtisDisplay
        }
        uploadImage()
    }

    private fun uploadImage() {
        paramImageId.let {
            Glide.with(this)
                .load("https://www.artic.edu/iiif/2/${it}/full/843,/0/default.jpg")
                .override(600, 600)
                .centerCrop()
                .placeholder(R.drawable.image_placeholder)
                .into(binding.mainImage)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(
            paramArticTitle: String, paramArtistDisplay: String, paramIdImage: String?
        ) = DetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM_TITLE, paramArticTitle)
                putString(ARG_PARAM_ARTIST, paramArtistDisplay)
                putString(ARG_PARAM_IMAGE_ID, paramIdImage)
            }
        }
    }
}