package com.bita.lost.ui.main

import android.graphics.*
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bita.lost.repo.data.AcquirePlaceCode
import com.bita.lost.repo.data.AcquisitionCode
import com.bita.lost.repo.data.ColorCode

object MainBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:acquirePlaceList")
    fun addAcquirePlaceList(recyclerView: RecyclerView, list: MutableList<AcquirePlaceCode>) {
        val adapter = recyclerView.adapter
        adapter?.let {
            if (adapter is AcquirePlaceAdapter) {
                adapter.set(list)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("app:colorList")
    fun addColorList(recyclerView: RecyclerView, list: MutableList<ColorCode>) {
        val adapter = recyclerView.adapter
        adapter?.let {
            if (adapter is ColorAdapter) {
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

    @JvmStatic
    @BindingAdapter("app:acquisitionList")
    fun addAcquisitionList(recyclerView: RecyclerView, list: MutableList<AcquisitionCode>) {
        val adapter = recyclerView.adapter
        adapter?.let {
            if (adapter is AcquisitionAdapter) {
                adapter.set(list)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("app:colorFilter")
    fun setColorFilter(view: View, @ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val colorFilter = BlendModeColorFilter(color, BlendMode.MULTIPLY)
            view.background.colorFilter = colorFilter
        } else {
            view.background.setColorFilter(color, PorterDuff.Mode.MULTIPLY)
        }
    }
}