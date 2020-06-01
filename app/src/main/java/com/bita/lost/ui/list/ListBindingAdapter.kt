package com.bita.lost.ui.list

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bita.lost.base.LActivity
import com.bita.lost.common.textAsBitmap
import com.bita.lost.repo.data.AcquirePlaceCode
import com.bita.lost.repo.data.AcquisitionCode
import com.bita.lost.repo.data.LostItem

object ListBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:items", "app:has_next", "app:get_next_data", "app:is_load_finish")
    fun setAdapter(
        v: RecyclerView,
        rawData: ArrayList<LostItem>,
        hasNext: Boolean,
        function: () -> Unit,
        isLoadFinish: Boolean
    ) {
        if (!isLoadFinish) {
            return
        }

        val data = arrayListOf<LostItem?>().apply {
            addAll(rawData)
            if (hasNext && isNotEmpty()) add(null)
            if (this.isEmpty()) add(null)
        }

        v.adapter?.let { it as? ListAdapter }?.set(data)
            ?: run {
                val adapter = ListAdapter(
                    { id -> v.context.let { it as? ListActivity }?.showDetail(id) },
                    function
                )
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

    @JvmStatic
    @BindingAdapter("app:setSrcByAcquirePlaceCode")
    fun setSrcByAcquirePlaceCode(v: ImageView, enum: AcquirePlaceCode) {
        if (enum == AcquirePlaceCode.직접입력) {
            v.setImageBitmap(enum.description.textAsBitmap(v.context, 10f, Color.BLACK))
        } else setSrc(v, enum.icon)
    }

    @JvmStatic
    @BindingAdapter("app:setSrcByAcquisitionCode")
    fun setSrcByAcquisitionCode(v: ImageView, enum: AcquisitionCode) {
        if (enum == AcquisitionCode.직접입력) {
            v.setImageBitmap(enum.description.textAsBitmap(v.context, 10f, Color.BLACK))
        } else setSrc(v, enum.icon)
    }

    @JvmStatic
    @BindingAdapter("app:onBack")
    fun onBack(v: View, flag: Boolean) {
        v.setOnClickListener { (v.context as? LActivity)?.onBackPressed() }
    }
}