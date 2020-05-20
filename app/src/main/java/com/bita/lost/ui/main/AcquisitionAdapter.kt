package com.bita.lost.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bita.lost.R
import com.bita.lost.base.SingleAdapter
import com.bita.lost.databinding.AcquisitionListItemBinding
import com.bita.lost.repo.AcquisitionCode

class AcquisitionAdapter : SingleAdapter<AcquisitionHolder, AcquisitionCode>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcquisitionHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = DataBindingUtil.inflate<AcquisitionListItemBinding>(inflater, R.layout.acquisition_list_item, parent, false)
        return AcquisitionHolder(view)
    }

    override fun onBind(holder: AcquisitionHolder, item: AcquisitionCode) = holder.bind(item)

}

class AcquisitionHolder(private val binding: AcquisitionListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: AcquisitionCode) {
        with(binding) {
            binding.item = item
            executePendingBindings()
        }
    }
}