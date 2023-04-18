package com.example.goncharov1.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.goncharov1.R
import com.example.goncharov1.data.utils.DownloadImageLoader
import com.example.goncharov1.databinding.ItemRecyclerViewBinding
import com.example.goncharov1.domain.entity.ArticEntity

class ArticListAdapter(
    val recyclerViewClickListener: RecyclerViewClickListener,
    val downloadImageLoader: DownloadImageLoader
) : RecyclerView.Adapter<ArticListAdapter.ArticViewHolder>() {

    private var listArticEntity = mutableListOf<ArticEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticViewHolder {
        val binding: ItemRecyclerViewBinding =
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticViewHolder, position: Int) {
        holder.bind(listArticEntity[position])
        holder.bindClick(listArticEntity[position])
    }

    override fun getItemCount(): Int = listArticEntity.size

    fun addListArtic(listArticEntity: List<ArticEntity>) {
        this.listArticEntity.addAll(listArticEntity)
        notifyDataSetChanged()
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

        fun bind(articEntity: ArticEntity) {

            with(binding) {
                textId.text =
                    contextView.getString(R.string.id_display_template, articEntity.id.toString())
                textTitle.text =
                    contextView.getString(R.string.title_display_template, articEntity.title)
                textArtistDisplay.text =
                    contextView.getString(
                        R.string.artist_display_template,
                        articEntity.artistDisplay
                    )
            }

            articEntity.imageId?.let {
                downloadImageLoader.downloadImage(
                    itemView.context.getString(R.string.main_url_for_upload_image, it),
                    R.drawable.image_placeholder,
                ).into(binding.mainImage)
            }
        }

        fun bindClick(itemArtic: ArticEntity) {
            binding.root.setOnClickListener {
                itemArtic.let {
                    recyclerViewClickListener.clickItemRecycler(it)
                }
            }
        }
    }
}