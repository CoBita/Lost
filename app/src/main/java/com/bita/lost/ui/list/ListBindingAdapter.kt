package com.bita.lost.ui.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.and.base.log.Log
import com.bita.lost.repo.data.LostItem

object ListBindingAdapter {

    @JvmStatic
    @BindingAdapter("list:items")
    fun setAdapter(v: RecyclerView, data: ArrayList<LostItem>) {
        v.adapter?.let {
            (it as ListAdapter).addAll(data)
        } ?: run {
            val adapter = ListAdapter()
            adapter.set(data)
            v.adapter = adapter
        }
        v.scheduleLayoutAnimation()
    }

    @JvmStatic
    @BindingAdapter("list:onBottom")
    fun onBottom(v: RecyclerView, function: () -> Unit) {
        Log.w("add scroll listener")
        v.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!v.canScrollVertically(1)) {
                    function()
                }
            }
        })
    }
}