package com.bita.lost.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bita.lost.R
import com.bita.lost.base.LFragment
import com.bita.lost.databinding.SearchFrBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFr : LFragment() {

    override val vm: SearchViewModel by viewModel()
    private val mainVm: MainViewModel by sharedViewModel()

    lateinit var bb: SearchFrBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.search_fr, container, false)
        return bb.root
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        bb.vm = vm
        vm.searchFinish.observe(this, Observer { mainVm.setSearchText(it) })
    }

    companion object {
        fun newInstance(): SearchFr {
            val searchFr = SearchFr()
            return searchFr
        }
    }
}