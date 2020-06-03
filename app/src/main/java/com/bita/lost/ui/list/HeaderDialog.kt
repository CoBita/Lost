package com.bita.lost.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bita.lost.R
import com.bita.lost.databinding.ListHeaderDlgBinding
import com.bita.lost.repo.data.AreaCode
import com.bita.lost.repo.data.ProductCode
import com.bita.lost.ui.main.AreaPickFr
import com.bita.lost.ui.main.ProductPickFr
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HeaderDialog private constructor() : BottomSheetDialogFragment() {
    // todo viewModel : HeaderBaseViewModel 생성 필요
    // todo 조건을 변경한 뒤 백 프래그먼트에게 어떻게 전달할지 확인하기
    private lateinit var binding: ListHeaderDlgBinding
    private lateinit var area: AreaCode
    private lateinit var product: ProductCode
    private lateinit var period: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_header_dlg, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.areaName = area.name
        binding.productName = product.name
        binding.displayPeriod = period
        // todo 조건 변경해서 재검색하는 기능 추가 필요
        binding.area.setOnClickListener { AreaPickFr.newInstance { }.show(childFragmentManager, "area") }
        binding.product.setOnClickListener { ProductPickFr.newInstance { }.show(childFragmentManager, "product") }
    }

    companion object {
        fun newInstance(pArea: AreaCode, pProduct: ProductCode, pPeriod: String): HeaderDialog = HeaderDialog().apply {
            area = pArea
            product = pProduct
            period = pPeriod
        }
    }
}