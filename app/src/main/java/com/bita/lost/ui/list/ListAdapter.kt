package com.bita.lost.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.and.base.log.Log
import com.bita.lost.R
import com.bita.lost.base.BaseAdapter
import com.bita.lost.base.BaseHolder
import com.bita.lost.databinding.ListItemBinding
import com.bita.lost.repo.data.LostItem

class ListAdapter(private val startActivity: (id: String) -> Unit) : BaseAdapter<LostItem>() {
    var lastAnimatedIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : BaseHolder<LostItem> =
        ListHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemViewType(position: Int): Int = 0

    inner class ListHolder(private val binding: ListItemBinding) :
        BaseHolder<LostItem>(binding.root) {
        override fun bind(data: LostItem) {
            val index = items.indexOf(data)
            if (lastAnimatedIndex < index) {
                binding.root.animation =
                    AnimationUtils.loadAnimation(binding.root.context, R.anim.up_anim)
                lastAnimatedIndex = index
            }
            binding.data = data
            binding.root.setOnClickListener { startActivity(data.id) }
        }
    }
}

