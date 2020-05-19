package com.bita.lost.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.bita.lost.R
import com.bita.lost.base.BaseAdapter
import com.bita.lost.base.BaseHolder
import com.bita.lost.databinding.ListItemBinding
import com.bita.lost.repo.data.LostItem

class ListAdapter : BaseAdapter<LostItem>() {
    val isAnimatedList = arrayListOf<Boolean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<LostItem> = ListHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemViewType(position: Int): Int = 0

    override fun set(items: ArrayList<LostItem>) {
        super.set(items)
        isAnimatedList.addAll(Array(items.size) { false })
    }

    override fun addAll(items: ArrayList<LostItem>) {
        super.addAll(items)
        isAnimatedList.addAll(Array(items.size) { false })
    }

    inner class ListHolder(private val binding: ListItemBinding) : BaseHolder<LostItem>(binding.root) {
        override fun bind(data: LostItem) {
            val index = items.indexOf(data)
            if (!isAnimatedList[index]) {
                binding.root.animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.appear_anim)
                isAnimatedList[index] = true
            }
            binding.data = data
        }
    }
}

