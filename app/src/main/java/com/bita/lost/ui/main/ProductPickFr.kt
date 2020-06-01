@file:Suppress("UnnecessaryVariable")

package com.bita.lost.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.and.base.common.EventObserver
import com.bita.lost.R
import com.bita.lost.databinding.ProductPickFrBinding
import com.bita.lost.repo.data.AreaCode
import com.bita.lost.repo.data.ProductCode
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductPickFr : BottomSheetDialogFragment() {

    private lateinit var bb: ProductPickFrBinding
    private val vm: ProductViewModel by viewModel()

    lateinit var onItemSelect: ((product: ProductCode) -> Unit)

    private val adapter by lazy {
        ProductAdapter().apply {
            onItemClick = {
                dismiss()
                onItemSelect.invoke(it)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.product_pick_fr, container, false)
        return bb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bb.vm = vm
        bb.header.headerVm = vm
        bb.recycler.adapter = adapter

        vm.getProductList()
        vm.dismiss.observe(this, EventObserver { dismiss() })
    }

    companion object {
        fun newInstance(itemSelect: ((product: ProductCode) -> Unit)): ProductPickFr {
            val productPickFr = ProductPickFr().apply { onItemSelect = itemSelect }
            return productPickFr
        }
    }
}