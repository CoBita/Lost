package com.bita.lost.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bita.lost.R
import com.bita.lost.base.LFragment
import com.bita.lost.databinding.ListFrBinding
import com.bita.lost.repo.AcquirePlaceCode
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment : LFragment() {
    override val vm: ListViewModel by sharedViewModel()
    private lateinit var binding  : ListFrBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fr, container, false)
        return binding.root
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        binding.vm = vm
    }

    override fun onParseExtra() {
        super.onParseExtra()
        // todo 메인에서 넘어온 값으로 변경 필요
        vm.init("핸드폰", AcquirePlaceCode.지하철1호선에서4호선, "")
    }

    override fun onLoad() {
        super.onLoad()
        vm.getLostList()
    }
}