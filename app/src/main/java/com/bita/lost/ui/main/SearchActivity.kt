package com.bita.lost.ui.main

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.and.base.log.Log
import com.bita.lost.R
import com.bita.lost.base.LActivity
import com.bita.lost.databinding.SearchActBinding
import com.bita.lost.repo.data.AreaCode
import com.bita.lost.repo.data.ProductCode
import com.bita.lost.ui.list.ListActivity
import kotlinx.android.synthetic.main.search_act.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SearchActivity : LActivity() {

    override val vm: SearchViewModel by viewModel()
    lateinit var bb: SearchActBinding


    private var areaCode: AreaCode? = null
    private var productCode: ProductCode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(mActivity, R.layout.search_act)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()

        area.setOnClickListener {
            AreaPickFr.newInstance {
                Log.i(it)
                area.setText(it.name)
                areaCode = it
            }.show(supportFragmentManager, AREA_PICK_TAG)
        }


        product.setOnClickListener {
            ProductPickFr.newInstance {
                Log.i(it)
                product.setText(it.name)
                productCode = it
            }.show(supportFragmentManager, PRODUCT_PICK_TAG)
        }
        startDate.setOnClickListener {
            showDatePicker {
                startDate.setText(it)
            }
        }
        endDate.setOnClickListener {
            showDatePicker {
                endDate.setText(it)
            }
        }
        search.setOnClickListener {
            search()
        }
    }


    private fun showDatePicker(dateResult: (result: String) -> Unit) {
        val cal = Calendar.getInstance()
        val dateDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            Log.i("year : $year, month : ${month + 1}, dayOfMonth : $dayOfMonth")
            val result = "$year.${month + 1}.$dayOfMonth"
            Log.i(result)
            dateResult.invoke(result)
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE))
        dateDialog.datePicker.maxDate = System.currentTimeMillis()
        dateDialog.show()
    }

    private fun search() {
        val sd = startDate.editableText.toString()
        val ed = endDate.editableText.toString()

        val intent = Intent(this, ListActivity::class.java).apply {
            putExtra(ListActivity.AREA, areaCode)
            putExtra(ListActivity.PRODUCT, productCode)
            putExtra(ListActivity.START_DATE, sd)
            putExtra(ListActivity.END_DATE, ed)
        }
        startActivity(intent)
    }

    companion object {
        const val AREA_PICK_TAG = "areaPick"
        const val PRODUCT_PICK_TAG = "productPick"
    }
}