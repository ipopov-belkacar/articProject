package com.example.goncharov1.ui.recycler

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.goncharov1.R
import com.example.goncharov1.data.utils.DownloadImageLoader
import com.example.goncharov1.databinding.ItemRecyclerViewBinding
import com.example.goncharov1.domain.entity.ArticEntity

class ArticListAdapter(
    val recyclerViewClickListener: RecyclerViewClickListener,
    val downloadImageLoader: DownloadImageLoader
) :
    PagingDataAdapter<ArticEntity, ArticListAdapter.ArticViewHolder>(articDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticViewHolder {
        return ArticViewHolder(parent)
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

    inner class ArticViewHolder(parent: ViewGroup) : SafeViewHolder(
        R.layout.item_recycler_view, parent
    ) {

        private val contextView = itemView.context

        private val binding: ItemRecyclerViewBinding by viewBinding()

        fun bind(itemArtic: ArticEntity?) {
            with(binding) {
                itemArtic?.let {
                    textId.text =
                        contextView.getString(R.string.id_display_template, it.id.toString())
                    textTitle.text =
                        contextView.getString(R.string.title_display_template, it.title)
                    textArtistDisplay.text =
                        contextView.getString(R.string.artist_display_template, it.artistDisplay)
                }
            }

            itemArtic?.imageId?.let {
                downloadImageLoader.downloadImage(
                    itemView.context.getString(R.string.main_url_for_upload_image, it),
                    R.drawable.image_placeholder,
                ).into(binding.mainImage)
            }
        }

        fun bindClick(itemArtic: ArticEntity?) {
            binding.root.setOnClickListener {
                recyclerViewClickListener.clickItemRecycler(itemArtic)
            }
        }
    }
}
