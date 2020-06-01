package com.bita.lost.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.and.base.common.EventObserver
import com.bita.lost.R
import com.bita.lost.databinding.ColorPickFrBinding
import com.bita.lost.repo.data.ColorCode
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ColorPickFr : BottomSheetDialogFragment() {

    private val vm: ColorPickViewModel by viewModel()
    lateinit var bb: ColorPickFrBinding

    // Item Select
    lateinit var onItemSelect: ((color: ColorCode) -> Unit)
    private val decoration by lazy { GridItemDecoration(16) }

    private val adapter: ColorAdapter by lazy {
        ColorAdapter().apply {
            onItemClick = {
                dismiss()
                onItemSelect.invoke(it)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.color_pick_fr, container, false)
        return bb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bb.vm = vm
        bb.recycler.adapter = adapter
        bb.recycler.addItemDecoration(decoration)
        vm.getColorList()

        vm.dismiss.observe(this, EventObserver { dismiss() })
    }

    override fun onDestroy() {
        super.onDestroy()
        bb.recycler.removeItemDecoration(decoration)
    }


    companion object {
        fun newInstance(itemSelect: ((color: ColorCode) -> Unit)): ColorPickFr {
            val colorPickFr = ColorPickFr().apply { onItemSelect = itemSelect }
            return colorPickFr
        }
    }
}