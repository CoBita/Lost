package com.bita.lost.common

import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bita.lost.R
import com.bumptech.glide.Glide

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

    @JvmStatic
    @BindingAdapter("app:src")
    fun setSrc(v: ImageView, drawable: Any?) {
        drawable?.let { Glide.with(v.context).load(it).into(v) }
    }

    @JvmStatic
    @BindingAdapter("app:underline")
    fun addUnderline(view: TextView, isUnderLine: Boolean?) {
        isUnderLine?.let {
            if (it)
                view.paintFlags = view.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }

}