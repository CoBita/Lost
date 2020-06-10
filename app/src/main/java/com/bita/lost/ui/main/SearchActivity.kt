package com.bita.lost.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.and.base.common.EventObserver
import com.bita.lost.R
import com.bita.lost.base.LActivity
import com.bita.lost.databinding.SearchActBinding
import com.bita.lost.repo.data.SearchResultData
import com.bita.lost.ui.list.ListAct
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : LActivity() {

    override val vm: SearchViewModel by viewModel()
    lateinit var bb: SearchActBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(mActivity, R.layout.search_act)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        bb.vm = vm
        vm.showBottomSheet.observe(this, Observer { it?.show(supportFragmentManager, "") })
        vm.showRangeDatePicker.observe(this, EventObserver { showRangeDatePicker() })
        vm.resultSearchData.observe(this, Observer { it?.let { data -> search(data) } })
    }

    private fun showRangeDatePicker() {
        val datePicker = MaterialDatePicker.Builder.dateRangePicker().apply {
            setCalendarConstraints(getCalendarConstraints())
            setTitleText("기간 선택")
        }.build()
        datePicker.addOnPositiveButtonClickListener(vm.datePickerListener())
        datePicker.show(supportFragmentManager, datePicker.tag)
    }

    private fun getCalendarConstraints(): CalendarConstraints {
        return CalendarConstraints.Builder().apply {
            setEnd(System.currentTimeMillis())
        }.build()
    }

    private fun search(data: SearchResultData) {
        val intent = Intent(this, ListAct::class.java).apply {
            putExtra(ListAct.AREA, data.areaCode)
            putExtra(ListAct.PRODUCT, data.productCode)
            putExtra(ListAct.START_DATE, data.startDate)
            putExtra(ListAct.END_DATE, data.endDate)
        }
        startActivity(intent)
    }
}