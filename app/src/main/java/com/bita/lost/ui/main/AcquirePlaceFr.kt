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

    private val acquirePlaceAdapter by lazy {
        //TODO : Animation 부분
        AcquirePlaceAdapter().apply { onItemClick = { mainVm.setAcquirePlaceData(it) } }
    }
    private val decoration = AcquirePlaceItemDecoration()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.acquire_place_fr, container, false)
        return bb.root
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        bb.vm = vm
        bb.recycler.adapter = acquirePlaceAdapter
        bb.recycler.addItemDecoration(decoration)
        vm.getAcquirePlaceCode()
    }


    companion object {
        fun newInstance(): AcquirePlaceFr {
            val acquirePlaceFr = AcquirePlaceFr()
            return acquirePlaceFr
        }
    }
}