package com.bita.lost.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.and.base.common.EventObserver
import com.and.base.component.SDF
import com.and.base.component.format
import com.and.base.log.Log
import com.bita.lost.R
import com.bita.lost.databinding.ListHeaderDlgBinding
import com.bita.lost.repo.data.AreaCode
import com.bita.lost.repo.data.ProductCode
import com.bita.lost.ui.main.AreaPickFr
import com.bita.lost.ui.main.ProductPickFr
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import kotlinx.android.synthetic.main.search_act.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListHeaderDialog : BottomSheetDialogFragment() {
    // todo 조건을 변경한 뒤 백 프래그먼트에게 어떻게 전달할지 확인하기
    private lateinit var binding: ListHeaderDlgBinding
    private lateinit var area: AreaCode
    private lateinit var product: ProductCode
    private lateinit var period: String

    private val vm: ListHeaderViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_header_dlg, container, false)
        binding.header.headerVm = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!::area.isInitialized || !::product.isInitialized || !::period.isInitialized)
            (activity as? ListAct)?.showError()
        else {
            binding.areaName = area.name
            binding.productName = product.name
            binding.displayPeriod = period
            // todo 조건 변경해서 재검색하는 기능 추가 필요 - 기간
            binding.area.setOnClickListener {
                AreaPickFr.newInstance {
                    (parentFragment as? ListFragment)?.changeParameter(it)
                    dismiss()
                }.show(childFragmentManager, "area")
            }
            binding.period.setOnClickListener { showRangeDatePicker() }
            binding.product.setOnClickListener {
                ProductPickFr.newInstance {
                    (parentFragment as? ListFragment)?.changeParameter(it)
                    dismiss()
                }.show(childFragmentManager, "product")
            }
        }

        vm.dismiss.observe(this, EventObserver { dismiss() })
    }


    private fun showRangeDatePicker() {
        val datePicker = MaterialDatePicker.Builder.dateRangePicker().apply {
            setCalendarConstraints(getCalendarConstraints())
            setTitleText("기간 선택")
        }.build()
        datePicker.addOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener {
            it?.let { pair ->
                val first = pair.first ?: 0
                val second = pair.second ?: 0

                val formatFirstDate = first.format(SDF.yyyymmdd_2)
                val formatSecondDate = second.format(SDF.yyyymmdd_2)
                Log.i("startDate : $formatFirstDate , endDate : $formatSecondDate")

                (parentFragment as? ListFragment)?.changeParameter(Pair(formatFirstDate, formatSecondDate))
                dismiss()
            }
        })
        datePicker.show(childFragmentManager, datePicker.tag)
    }

    private fun getCalendarConstraints(): CalendarConstraints {
        return CalendarConstraints.Builder().apply {
            setEnd(System.currentTimeMillis())
        }.build()
    }

    companion object {
        fun newInstance(pArea: AreaCode?, pProduct: ProductCode?, pPeriod: String?): ListHeaderDialog = ListHeaderDialog().apply {
            area = pArea ?: AreaCode.전체지역
            product = pProduct ?: ProductCode.모든습득물
            period = pPeriod ?: ""
        }
    }
}