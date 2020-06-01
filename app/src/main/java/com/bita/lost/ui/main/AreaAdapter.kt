package com.bita.lost.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bita.lost.R
import com.bita.lost.base.SingleAdapter
import com.bita.lost.databinding.AreaListItemBinding
import com.bita.lost.repo.data.AreaCode

class AreaAdapter : SingleAdapter<AreaHolder, AreaCode>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<AreaListItemBinding>(inflater, R.layout.area_list_item, parent, false)
        return AreaHolder(binding)
    }

    override fun onBind(holder: AreaHolder, item: AreaCode) = holder.bind(item)

}


class AreaHolder(private val binding: AreaListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AreaCode) {
        with(binding) {
            this.item = item
            executePendingBindings()
        }
    }
}