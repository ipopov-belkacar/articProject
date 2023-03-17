package com.example.goncharov1.ui.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goncharov1.databinding.ItemRecyclerViewBinding
import com.example.goncharov1.domain.entity.ArticEntity

class ArticListAdapter :
    RecyclerView.Adapter<ArticListAdapter.ArticViewHolder>() {

    private var listArtic: List<ArticEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticViewHolder {
        val binding: ItemRecyclerViewBinding =
            ItemRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticViewHolder, position: Int) {
        with(holder.binding) {
            listArtic[position].let {
                textId.text = it.id.toString()
                textTitle.text = it.title
                textArtistDisplay.text = it.artistDisplay
            }
        }
    }

    override fun getItemCount(): Int = listArtic.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateListArtic(list: List<ArticEntity>) {
        this.listArtic = list
        notifyDataSetChanged()
    }

    class ArticViewHolder(var binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}