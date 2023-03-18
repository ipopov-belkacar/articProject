package com.example.goncharov1.ui.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.goncharov1.domain.entity.ArticEntity

class ArticDiffUtil(
    private val oldList: List<ArticEntity>,
    private val newList: List<ArticEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].artistDisplay == newList[newItemPosition].artistDisplay
    }
}