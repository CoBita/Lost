package com.bita.lost.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bita.lost.R
import com.bita.lost.base.LFragment
import com.bita.lost.databinding.AcquisitionFrBinding
import com.bita.lost.repo.data.AcquisitionCode
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AcquisitionFr : LFragment() {

    override val vm: AcquisitionViewModel by viewModel()
    private val mainVm: MainViewModel by sharedViewModel()

    lateinit var bb: AcquisitionFrBinding

    private val acquisitionAdapter by lazy {
        AcquisitionAdapter().apply {
            onItemClick = {
                if (it == AcquisitionCode.직접입력) {
                    val inputBottomSheetFr = InputBottomSheetFr.newInstance(inputTitle) { input ->
                        it.description = input
                        mainVm.setAcquisitionData(it)
                    }
                    inputBottomSheetFr.show(childFragmentManager, "")
                } else {
                    mainVm.setAcquisitionData(it)
                }
            }
        }
    }

    private val itemDecoration by lazy { GridItemDecoration(16) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.acquisition_fr, container, false)
        return bb.root
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        bb.vm = vm
        bb.recycler.adapter = acquisitionAdapter
        bb.recycler.addItemDecoration(itemDecoration)
        vm.getAcquisitionCode()
    }


    companion object {
        const val inputTitle = "잃어버린 물건"
        fun newInstance(): AcquisitionFr {
            val acquisitionFr = AcquisitionFr()
            return acquisitionFr
        }
    }
}