package com.bita.lost.ui.main

import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bita.lost.repo.data.AcquireData

object MainBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:acquirePlaceList")
    fun addAcquirePlaceList(recyclerView: RecyclerView, list: MutableList<AcquireData>) {
        val adapter = recyclerView.adapter
        adapter?.let {
            if (adapter is AcquirePlaceAdapter) {
                adapter.set(list)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("app:drawableTop")
    fun topDrawable(view: TextView, @DrawableRes resId: Int) {
        val context = view.context
        val drawable = ContextCompat.getDrawable(context, resId)
        drawable?.let {
            view.setCompoundDrawablesWithIntrinsicBounds(null, it, null, null)
        }
    }
}