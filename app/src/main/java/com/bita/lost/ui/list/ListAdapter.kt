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

class ListAdapter(private val showDetail: (id: String, seq: Int) -> Unit) : BaseAdapter<Any>() {
    var lastAnimatedIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Any> {
        return when (viewType) {
            0 -> ListHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            1 -> NoHolder(ListNoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> MoreHolder(ListMoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int =
            when (getItem(position)) {
                is LostItem -> 0
                BaseHolderType.결과없음 -> 1
                BaseHolderType.더보기 -> 2
                else -> 2
            }


    private fun animate(v: View, index: Int) {
        if (lastAnimatedIndex < index) {
            v.animation = AnimationUtils.loadAnimation(v.context, R.anim.up_anim)
            lastAnimatedIndex = index
        }
    }

    inner class ListHolder(private val binding: ListItemBinding) : BaseHolder<Any>(binding.root) {
        override fun bind(data: Any) {
            animate(binding.root, items.indexOf(data))
            (data as? LostItem)?.let {
                binding.data = it
                binding.root.setOnClickListener { _ -> showDetail(it.atcId, it.fdSn) }
            }
        }
    }

    inner class NoHolder(private val binding: ListNoItemBinding) : BaseHolder<Any>(binding.root) {
        override fun bind(data: Any) {
            animate(binding.notice, items.indexOf(data))
        }
    }

    inner class MoreHolder(private val binding: ListMoreItemBinding) : BaseHolder<Any>(binding.root) {
        override fun bind(data: Any) {
            animate(binding.root, items.indexOf(data))
            binding.root.setOnClickListener {
                lastAnimatedIndex--
                val lastIndex = items.lastIndex
                items.removeAt(lastIndex)
                notifyItemRemoved(lastIndex)
            }
        }
    }
}

