package com.bita.lost.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Holder of BaseAdapter
 */
abstract class BaseHolder<VH>(v : View) : RecyclerView.ViewHolder(v) {
    abstract fun bind(data : VH)
}