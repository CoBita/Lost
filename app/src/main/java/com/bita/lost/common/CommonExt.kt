package com.bita.lost.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.lifecycle.MutableLiveData
import com.and.base.common.sp2px
import kotlinx.coroutines.Job


/**
 * 네트워크 로딩 시 progress 보여주는 함수
 */
fun Job.progress(_isProgress: MutableLiveData<Boolean>): Job {
    _isProgress.postValue(true)
    this.invokeOnCompletion { _isProgress.postValue(false) }
    return this
}


fun String.textAsBitmap(context: Context, textSize: Float, @ColorInt textColor: Int): Bitmap {
    val size = context.sp2px(textSize)
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.textSize = size.toFloat()
        this.color = textColor
        this.textAlign = Paint.Align.LEFT
    }
    val baseline = -paint.ascent()
    var width = (paint.measureText(this) + 0.0f).toInt() // round
    var height = (baseline + paint.descent() + 0.0f).toInt()

    val trueWidth = width
    if (width > height) height = width else width = height
    val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(image)
    canvas.drawText(this, width / 2 - trueWidth / 2.toFloat(), baseline, paint)
    return image
}
