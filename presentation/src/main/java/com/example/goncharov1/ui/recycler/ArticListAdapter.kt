package com.example.goncharov1.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.goncharov1.databinding.ItemRecyclerViewBinding
import com.example.goncharov1.domain.entity.ArticEntity

class ArticListAdapter :
    PagingDataAdapter<ArticEntity, ArticListAdapter.ArticViewHolder>(articDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticViewHolder {
        val binding: ItemRecyclerViewBinding =
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticViewHolder, position: Int) {
        with(holder.binding) {

            getItem(position)!!.let {
                textId.text = it.id.toString()
                textTitle.text = it.title
                textArtistDisplay.text = it.artistDisplay
            }

            getItem(position)!!.imageId?.let {
                Glide
                    .with(holder.itemView.context)
                    .load("https://www.artic.edu/iiif/2/${it}/full/843,/0/default.jpg")
                    .into(mainImage)
            }
        }
    }

    companion object {
        private val articDiffUtil = object : DiffUtil.ItemCallback<ArticEntity>() {
            override fun areItemsTheSame(oldItem: ArticEntity, newItem: ArticEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ArticEntity, newItem: ArticEntity): Boolean {
                return oldItem.artistDisplay == newItem.artistDisplay && oldItem.title == newItem.title
            }
        }
    }

    class ArticViewHolder(var binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}