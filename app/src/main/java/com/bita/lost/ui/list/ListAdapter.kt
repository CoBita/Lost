package com.bita.lost.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.and.base.log.Log
import com.bita.lost.R
import com.bita.lost.base.BaseAdapter
import com.bita.lost.base.BaseHolder
import com.bita.lost.databinding.ListItemBinding
import com.bita.lost.databinding.ListNoItemBinding
import com.bita.lost.repo.data.LostItem

class ListAdapter(private val showDetail: (id: String, seq: Int, title : String) -> Unit) : BaseAdapter<Any>() {
    var lastAnimatedIndex = -1

    init {
        // 깜빡임 현상 막기
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Any> {
        return when (viewType) {
            0 -> ListHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> NoHolder(ListNoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is LostItem -> 0
        BaseHolderType.결과없음 -> 1
        else -> 1
    }


    override fun getItemId(position: Int): Long = getItem(position).hashCode().toLong()

    // item 애니메이션
    private fun animate(v: View, index: Int) {
        if (lastAnimatedIndex < index) {
            v.animation = AnimationUtils.loadAnimation(v.context, R.anim.up_anim)
            lastAnimatedIndex = index
        } else v.clearAnimation()
    }

    inner class ListHolder(private val binding: ListItemBinding) : BaseHolder<Any>(binding.root) {
        override fun bind(data: Any) {
            animate(binding.root, items.indexOf(data))
            (data as? LostItem)?.let {
                binding.data = it
                binding.root.setOnClickListener { _ -> showDetail(it.atcId, it.fdSn, it.fdPrdtNm) }
            }
        }
    }

    inner class NoHolder(private val binding: ListNoItemBinding) : BaseHolder<Any>(binding.root) {
        override fun bind(data: Any) {
            animate(binding.notice, items.indexOf(data))
        }
    }
}

