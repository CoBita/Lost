package com.bita.lost.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.bita.lost.R
import com.bita.lost.base.BaseAdapter
import com.bita.lost.base.BaseHolder
import com.bita.lost.databinding.ListItemBinding
import com.bita.lost.databinding.ListMoreItemBinding
import com.bita.lost.databinding.ListNoItemBinding
import com.bita.lost.repo.data.LostItem

class ListAdapter(private val startActivity: (id: String) -> Unit,
                  private val getNextData: () -> Unit) : BaseAdapter<LostItem?>() {
    var lastAnimatedIndex = -1

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<LostItem?> {
        return when (viewType) {
            0 -> ListHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            1 -> NoHolder(ListNoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> MoreHolder(ListMoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int = getItem(position)?.let { 0 } ?: if(position == 0) 1 else 1

    private fun animate(v: View, index: Int) {
        if (lastAnimatedIndex < index) {
            v.animation = AnimationUtils.loadAnimation(v.context, R.anim.up_anim)
            lastAnimatedIndex = index
        }
    }

    inner class ListHolder(private val binding: ListItemBinding) :
            BaseHolder<LostItem?>(binding.root) {
        override fun bind(data: LostItem?) {
            animate(binding.root, items.indexOf(data))
            binding.data = data
            binding.root.setOnClickListener { data?.atcId?.let { id -> startActivity(id) } }
        }
    }

    inner class NoHolder(private val binding: ListNoItemBinding) : BaseHolder<LostItem?>(binding.root) {
        override fun bind(data: LostItem?) {
            animate(binding.notice, items.indexOf(data))
        }
    }

    inner class MoreHolder(private val binding: ListMoreItemBinding) : BaseHolder<LostItem?>(binding.root) {
        override fun bind(data: LostItem?) {
            animate(binding.root, items.indexOf(data))
            binding.root.setOnClickListener {
                lastAnimatedIndex--
                val lastIndex = items.lastIndex
                items.removeAt(lastIndex)
                notifyItemRemoved(lastIndex)
                getNextData()
            }
        }
    }
}

