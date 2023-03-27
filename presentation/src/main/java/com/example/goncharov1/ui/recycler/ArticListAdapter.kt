package com.example.goncharov1.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.goncharov1.R
import com.example.goncharov1.databinding.ItemRecyclerViewBinding
import com.example.goncharov1.domain.entity.ArticEntity

class ArticListAdapter(val recyclerViewClickListener: RecyclerViewClickListener) :
    PagingDataAdapter<ArticEntity, ArticListAdapter.ArticViewHolder>(articDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticViewHolder {
        val binding: ItemRecyclerViewBinding =
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.bindClick(getItem(position))
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

    inner class ArticViewHolder(var binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val contextView = itemView.context

        fun bind(itemArtic: ArticEntity?) {
            with(binding) {
                itemArtic?.let {
                    textId.text = contextView.getString(R.string.id_display_template, it.id.toString())
                    textTitle.text = contextView.getString(R.string.title_display_template, it.title)
                    textArtistDisplay.text = contextView.getString(R.string.artist_display_template, it.artistDisplay)
                }
            }

            itemArtic?.imageId?.let {
                Glide
                    .with(itemView.context)
                    .load(contextView.getString(R.string.main_url_for_upload_image, it))
                    .override(
                        contextView.getString(R.string.standard_image_width).toInt(),
                        contextView.getString(R.string.standard_image_height).toInt())
                    .centerCrop()
                    .placeholder(R.drawable.image_placeholder)
                    .into(binding.mainImage)
            }
        }

        fun bindClick(itemArtic: ArticEntity?) {
            binding.root.setOnClickListener {
                recyclerViewClickListener.clickItemRecycler(itemArtic)
            }
        }
    }
}