package com.bita.lost.ui.list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bita.lost.repo.data.AcquirePlaceCode
import com.bita.lost.repo.data.LostItem

object ListBindingAdapter {

    //    /*"app:has_next",*/
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("app:items", "app:has_next", "app:get_next_data")
    fun setAdapter(v: RecyclerView, rawData: ArrayList<LostItem>, hasNext: Boolean, function: () -> Unit) {
        // todo hasNext 추가
        val data = arrayListOf<LostItem?>().apply {
            addAll(rawData)
            if (hasNext && isNotEmpty()) add(null)
        }

        v.adapter?.takeIf { it is ListAdapter }?.let {
            (it as ListAdapter).addAll(data)
        } ?: run {
            val adapter = ListAdapter({ id ->
                v.context.takeIf { it is ListActivity }?.let {
                    (it as ListActivity).replace(true, id)
                }
            }, function)
            adapter.set(data)
            v.adapter = adapter
        }
        v.scheduleLayoutAnimation()
    }

    @JvmStatic
    @BindingAdapter("app:setTransport")
    fun setTransport(v: TextView, enum: AcquirePlaceCode) {
        v.apply {
            text = enum.description
            setCompoundDrawablesRelativeWithIntrinsicBounds(null, v.context.getDrawable(enum.icon), null, null)
        }
    }
}