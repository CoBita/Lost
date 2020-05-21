package com.bita.lost.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bita.lost.R
import com.bita.lost.base.SingleAdapter
import com.bita.lost.databinding.AcquireListItemBinding
import com.bita.lost.repo.data.AcquirePlaceCode

class AcquirePlaceAdapter : SingleAdapter<AcquirePlaceHolder, AcquirePlaceCode>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcquirePlaceHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<AcquireListItemBinding>(inflater, R.layout.acquire_list_item, parent, false)
        return AcquirePlaceHolder(binding)
    }

    override fun onBind(holder: AcquirePlaceHolder, item: AcquirePlaceCode) = holder.bind(item)

}


class AcquirePlaceHolder(private val binding: AcquireListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: AcquirePlaceCode) {
        with(binding) {
            binding.item = item
            executePendingBindings()
        }
    }
}