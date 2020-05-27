package com.bita.lost.common

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bita.lost.R

object CommonBinding {
    @JvmStatic
    @BindingAdapter("app:intSrc")
    fun setIntSrc(view: ImageView, @DrawableRes resId: Int) {
        view.setImageResource(resId)
    }

    @JvmStatic
    @BindingAdapter("app:mainImage")
    fun setMainImage(view: ImageView, image: Any?) {
        val context = view.context
        image?.let {
            when (image) {
                is Int -> view.setImageResource(image)
                is String -> {
                    val color = ContextCompat.getColor(context, R.color.black)
                    val bitmap = image.textAsBitmap(context, 16f, color)
                    view.setImageBitmap(bitmap)
                }

                else -> throw IllegalArgumentException("정상적인 Image가 아닙니다.")
            }
        }
    }
}