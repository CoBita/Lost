package com.bita.lost.ui.main

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bita.lost.R
import com.bita.lost.base.LActivity
import com.bita.lost.databinding.SearchActBinding
import com.bita.lost.repo.data.SearchResultData
import com.bita.lost.ui.list.ListAct
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

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
        vm.showDatePicker.observe(this, Observer { it?.let { result -> showDatePicker(result) } })
        vm.resultSearchData.observe(this, Observer { it?.let { data -> search(data) } })
    }


    private fun showDatePicker(dateResult: (result: String) -> Unit) {
        val cal = Calendar.getInstance()
        val dateDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val result = "$year.${month + 1}.$dayOfMonth"
            dateResult.invoke(result)
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE))
        dateDialog.datePicker.maxDate = System.currentTimeMillis()
        dateDialog.show()
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