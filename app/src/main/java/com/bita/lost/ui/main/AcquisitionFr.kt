package com.bita.lost.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bita.lost.R
import com.bita.lost.base.LFragment
import com.bita.lost.databinding.AcquirePlaceFrBinding
import com.bita.lost.databinding.AcquisitionFrBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AcquisitionFr : LFragment() {

    override val vm: AcquisitionViewModel by viewModel()
    private val mainVm: MainViewModel by sharedViewModel()

    lateinit var bb: AcquisitionFrBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.acquisition_fr, container, false)
        return bb.root
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
    }


    companion object {
        fun newInstance(): AcquisitionFr {
            val acquisitionFr = AcquisitionFr()
            return acquisitionFr
        }
    }
}