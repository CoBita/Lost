package com.bita.lost.common

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

object CommonBinding {
    @JvmStatic
    @BindingAdapter("app:intSrc")
    fun setIntSrc(view: ImageView, @DrawableRes resId: Int) {
        view.setImageResource(resId)
    }
}