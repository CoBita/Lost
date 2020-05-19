package com.bita.lost.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Recyclerview Base Adapter
 */
abstract class BaseAdapter<T>() : RecyclerView.Adapter<BaseHolder<T>>() {
    protected var items: MutableList<T> = mutableListOf()

    fun set(items: ArrayList<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addAll(items: ArrayList<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T>

    abstract override fun getItemViewType(position: Int): Int

    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) =
        onBind(holder, items[position])

    protected fun getItem(position: Int): T = items[position]

    override fun getItemCount(): Int = items.size

    private fun onBind(holder: BaseHolder<T>, item: T) = holder.bind(data = item)
}
