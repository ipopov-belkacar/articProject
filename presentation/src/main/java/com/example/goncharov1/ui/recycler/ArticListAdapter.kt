package com.example.goncharov1.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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

    fun updateListArtic(newListArtic: List<ArticEntity>) {
        val diffUtil = ArticDiffUtil(oldList = listArtic, newList = newListArtic)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        diffResult.dispatchUpdatesTo(this)
        this.listArtic = newListArtic
    }

    class ArticViewHolder(var binding: ItemRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}