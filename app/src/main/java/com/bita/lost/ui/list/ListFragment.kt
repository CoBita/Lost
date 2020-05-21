package com.bita.lost.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bita.lost.R
import com.bita.lost.base.LFragment
import com.bita.lost.databinding.ListFrBinding
import com.bita.lost.repo.data.AcquirePlaceCode
import com.bita.lost.repo.data.AcquisitionCode
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment : LFragment() {
    override val vm: ListViewModel by sharedViewModel()
    private lateinit var binding: ListFrBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fr, container, false)
        return binding.root
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        binding.vm = vm
        vm.getFirstLostList()
    }

    override fun onParseExtra() {
        super.onParseExtra()
        // todo 메인에서 넘어온 값으로 변경 필요
        val wbCode = activity?.intent?.getSerializableExtra(ListActivity.EXTRA_ACQUIRE_PLACE)
        val cate = activity?.intent?.getSerializableExtra(ListActivity.EXTRA_ACQUISITION)
        val name = activity?.intent?.getStringExtra(ListActivity.EXTRA_SEARCH)

        if (wbCode is AcquirePlaceCode && cate is AcquisitionCode) {
            vm.init(cate, wbCode, name)
        }
    }
}