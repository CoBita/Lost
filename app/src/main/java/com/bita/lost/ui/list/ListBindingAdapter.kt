package com.bita.lost.ui.list

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bita.lost.repo.data.LostItem

object ListBindingAdapter {

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("app:items", "app:has_next", "app:get_next_data", "app:is_load_finish")
    fun setAdapter(v: RecyclerView, rawData: ArrayList<LostItem>, hasNext: Boolean, function: () -> Unit, isLoadFinish: Boolean) {
        if (!isLoadFinish) {
            return
        }

        val data = arrayListOf<LostItem?>().apply {
            addAll(rawData)
            if (hasNext && isNotEmpty()) add(null)
            if (this.isEmpty()) add(null)
        }

        v.adapter?.takeIf { it is ListAdapter }?.let {
            (it as ListAdapter).set(data)
        } ?: run {
            val adapter = ListAdapter({ id ->
                v.context.takeIf { it is ListActivity }?.let {
                    (it as ListActivity).detailReplace(id)
                }
            }, function)
            adapter.set(data)
            v.adapter = adapter
        }
        v.scheduleLayoutAnimation()
    }

    @JvmStatic
    @BindingAdapter("app:src")
    fun setSrc(v: ImageView, drawableId: Int) {
        v.setImageResource(drawableId)
    }
}