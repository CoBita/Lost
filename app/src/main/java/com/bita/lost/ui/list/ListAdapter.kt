package com.bita.lost.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bita.lost.base.BaseAdapter
import com.bita.lost.base.BaseHolder
import com.bita.lost.databinding.ListItemBinding
import com.bita.lost.repo.data.LostItem

class ListAdapter : BaseAdapter<LostItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<LostItem> = ListHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemViewType(position: Int): Int = 0
}

class ListHolder(private val binding: ListItemBinding) : BaseHolder<LostItem>(binding.root) {
    override fun bind(data: LostItem) {
        binding.data = data
    }
}