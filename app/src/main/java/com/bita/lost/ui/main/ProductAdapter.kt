package com.bita.lost.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bita.lost.R
import com.bita.lost.base.SingleAdapter
import com.bita.lost.databinding.ProductIistItemBinding
import com.bita.lost.repo.data.ProductCode

class ProductAdapter : SingleAdapter<ProductHolder, ProductCode>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = DataBindingUtil.inflate<ProductIistItemBinding>(inflater, R.layout.product_iist_item, parent, false)
        return ProductHolder(binding)
    }

    override fun onBind(holder: ProductHolder, item: ProductCode) = holder.bind(item)

}


class ProductHolder(private val binding: ProductIistItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ProductCode) {
        with(binding) {
            this.item = item
            executePendingBindings()
        }
    }
}