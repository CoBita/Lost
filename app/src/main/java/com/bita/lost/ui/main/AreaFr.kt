@file:Suppress("UnnecessaryVariable")

package com.bita.lost.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.and.base.common.EventObserver
import com.bita.lost.R
import com.bita.lost.databinding.AreaFrBinding
import com.bita.lost.repo.data.AreaCode
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AreaFr : BottomSheetDialogFragment() {

    private lateinit var bb: AreaFrBinding
    private val vm: AreaViewModel by viewModel()

    lateinit var onItemSelect: ((area: AreaCode) -> Unit)

    private val adapter by lazy {
        AreaAdapter().apply {
            onItemClick = {
                dismiss()
                onItemSelect.invoke(it)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.area_fr, container, false)
        return bb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bb.vm = vm
        bb.header.headerVm = vm
        bb.recycler.adapter = adapter

        vm.getAreaList()
        vm.dismiss.observe(this, EventObserver { dismiss() })
    }

    companion object {
        fun newInstance(itemSelect: ((area: AreaCode) -> Unit)): AreaFr {
            val areaFr = AreaFr().apply { onItemSelect = itemSelect }
            return areaFr
        }
    }
}