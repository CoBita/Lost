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
import java.util.*
import kotlin.collections.ArrayList

class ListAdapter(private val showDetail: (id: String, seq: Int, title: String) -> Unit) : BaseAdapter<Any>() {
    private var _lastAnimatedIndex = -1
    private var _lastVisibleIndex = -1
    private val queueOfView: Queue<Pair<Int, View>> = LinkedList()

    init {
        // 깜빡임 현상 막기
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = getItem(position).hashCode().toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Any> {
        return when (viewType) {
            0 -> ListHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> NoHolder(ListNoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun set(items: ArrayList<Any>) {
        super.set(items)
        queueOfView.clear()
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is LostItem -> 0
        BaseHolderType.결과없음 -> 1
        else -> 1
    }

    fun setLastVisibleIndex(index: Int) {
        _lastVisibleIndex = index
        if (queueOfView.isEmpty())
            return
        val count = if (_lastAnimatedIndex == -1) _lastVisibleIndex + 1 else _lastVisibleIndex - _lastAnimatedIndex
        loop@ for (i in queueOfView.indices) {
            val pair = queueOfView.peek() ?: break@loop
            if (pair.first <= _lastVisibleIndex) {
                queueOfView.poll()?.let { target -> target.second.animation = AnimationUtils.loadAnimation(target.second.context, R.anim.up_anim) }
                _lastAnimatedIndex = index
            } else break@loop
        }
    }

    // item 애니메이션
    private fun animate(v: View, index: Int) {
        if (_lastAnimatedIndex < index) {
            if (_lastVisibleIndex < index) {
                queueOfView.add(Pair(index, v))
            } else {
                v.animation = AnimationUtils.loadAnimation(v.context, R.anim.up_anim)
                _lastAnimatedIndex = index
            }
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

