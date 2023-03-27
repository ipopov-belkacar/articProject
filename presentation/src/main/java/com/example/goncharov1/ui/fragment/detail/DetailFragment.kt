package com.example.goncharov1.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.goncharov1.R
import com.example.goncharov1.databinding.FragmentDetailBinding
import com.example.goncharov1.domain.entity.ArticEntity
import com.google.gson.Gson

private const val ARG_PARAM_ARTIC_ITEM = "paramArticItem"

class DetailFragment : Fragment() {

    private var articItem: ArticEntity? = null

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articItem = Gson().fromJson(it.getString(ARG_PARAM_ARTIC_ITEM), ArticEntity::class.java)
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
            textArtistDisplay.text = getString(R.string.artist_display_template, articItem?.artistDisplay)
            textTitle.text = getString(R.string.title_display_template, articItem?.title)
        }
        uploadImage()
    }

    private fun uploadImage() {
        articItem?.imageId.let {
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
            itemArtic: String
        ) = DetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM_ARTIC_ITEM, itemArtic)
            }
        }
    }
}