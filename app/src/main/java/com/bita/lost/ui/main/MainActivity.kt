package com.bita.lost.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bita.lost.R
import com.bita.lost.base.LActivity
import com.bita.lost.databinding.ActivityMainBinding
import com.bita.lost.repo.data.MainResultData
import com.bita.lost.ui.list.ListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : LActivity() {
    override val vm: MainViewModel by viewModel()
    lateinit var bb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(mActivity, R.layout.activity_main)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        bb.vm = vm
        // First
        replace(AcquirePlaceFr.newInstance(), ACQUIRE_PLACE_TAG)
        vm.selectAcquirePlaceData.observe(this, Observer { it?.let { replace(AcquisitionFr.newInstance(), ACQUISITION_TAG) } })
        vm.selectAcquisitionData.observe(this, Observer { it?.let { replace(SearchFr.newInstance(), SEARCH_TAG) } })
        vm.finish.observe(this, Observer { it?.let { mainResultData -> mainFinish(mainResultData) } })
    }


    private fun replace(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment, tag)
            commit()
        }
    }

    private fun mainFinish(mainResultData: MainResultData) {
        val acquirePlaceData = mainResultData.acquirePlaceCode
        val acquisitionData = mainResultData.acquisitionCode
        val searchText = mainResultData.search

        val intent = Intent(this, ListActivity::class.java).apply {
            putExtra(ListActivity.EXTRA_ACQUIRE_PLACE, acquirePlaceData)
            putExtra(ListActivity.EXTRA_ACQUISITION, acquisitionData)
            putExtra(ListActivity.EXTRA_SEARCH, searchText)
        }
        startActivity(intent)
    }

    companion object {
        const val ACQUIRE_PLACE_TAG = "AcquirePlace"
        const val ACQUISITION_TAG = "Acquisition"
        const val SEARCH_TAG = "Search"
    }

}
