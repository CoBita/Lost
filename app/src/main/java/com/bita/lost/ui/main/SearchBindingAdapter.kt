package com.bita.lost.ui.main

import android.graphics.*
import android.os.Build
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bita.lost.repo.data.*

object SearchBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:areaList")
    fun addAreaList(recyclerView: RecyclerView, list: MutableList<AreaCode>) {
        val adapter = recyclerView.adapter
        adapter?.let {
            if (adapter is AreaAdapter) {
                adapter.set(list)
            }
        }
    }


    @JvmStatic
    @BindingAdapter("app:productList")
    fun addProductList(recyclerView: RecyclerView, list: MutableList<ProductCode>) {
        val adapter = recyclerView.adapter
        adapter?.let {
            if (adapter is ProductAdapter) {
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
    @BindingAdapter("app:drawableStart")
    fun startDrawable(view: TextView, @DrawableRes resId: Int) {
        val context = view.context
        val drawable = ContextCompat.getDrawable(context, resId)
        drawable?.let {
            view.setCompoundDrawablesWithIntrinsicBounds(it, null, null, null)
        }
    }

    @Suppress("DEPRECATION")
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

    @JvmStatic
    @BindingAdapter("app:hintColor")
    fun setHintColor(view: EditText, @ColorInt color: Int) {
        view.setHintTextColor(color)
    }
}