package com.bita.lost.ui.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.and.base.log.Log
import com.bita.lost.repo.data.LostItem

object ListBindingAdapter {

    @JvmStatic
    @BindingAdapter("list:items")
    fun setAdapter(v: RecyclerView, data: ArrayList<LostItem>) {
        Log.w("setAdapter called=============\n" + data.joinToString("\n"))
        v.adapter?.let {
            (it as ListAdapter).set(data)
        } ?: run {
            val adapter = ListAdapter()
            adapter.set(data)
            v.adapter = adapter
        }
    }
}