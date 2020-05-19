package com.bita.lost.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bita.lost.R
import com.bita.lost.base.LFragment
import com.bita.lost.databinding.AcquirePlaceFrBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AcquirePlaceFr : LFragment() {

    override val vm: AcquirePlaceViewModel by viewModel()
    private val mainVm: MainViewModel by sharedViewModel()

    lateinit var bb: AcquirePlaceFrBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.acquire_place_fr, container, false)
        return bb.root
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        vm.getAcquirePlaceCode()
    }
}