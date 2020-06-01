package com.bita.lost.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bita.lost.R
import com.bita.lost.base.SingleAdapter
import com.bita.lost.databinding.ColorListItemBinding
import com.bita.lost.repo.data.ColorCode


class ColorAdapter : SingleAdapter<ColorHolder, ColorCode>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<ColorListItemBinding>(inflater, R.layout.color_list_item, parent, false)
        return ColorHolder(binding)
    }

    override fun onBind(holder: ColorHolder, item: ColorCode) = holder.bind(item)
}


class ColorHolder(private val binding: ColorListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ColorCode) {
        with(binding) {
            this.item = item
            executePendingBindings()
        }
    }
}