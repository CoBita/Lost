package com.bita.lost.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.and.base.log.Log
import com.bita.lost.R
import com.bita.lost.base.LActivity
import com.bita.lost.databinding.ActivityMainBinding
import com.bita.lost.repo.data.MainResultData
import com.bita.lost.ui.list.ListActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : LActivity() {
    override val vm: MainViewModel by viewModel()
    lateinit var bb: ActivityMainBinding

    override fun setSoftInputMode() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(mActivity, R.layout.activity_main)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        bb.vm = vm
        // First
        vm.replaceFragment.observe(this, Observer { it?.let { replace(it) } })
        vm.finish.observe(this, Observer { it?.let { mainResultData -> mainFinish(mainResultData) } })
    }

    private fun replace(tag: String) {
        val fragment: Fragment =
                when (tag) {
                    MainViewModel.ACQUIRE_PLACE_TAG -> AcquirePlaceFr.newInstance()
                    MainViewModel.ACQUISITION_TAG -> AcquisitionFr.newInstance()
                    else -> throw IllegalStateException("들어오면 안됨")
                }
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment, tag)
            commit()
        }
    }


    private fun mainFinish(mainResultData: MainResultData) {
        val acquirePlaceData = mainResultData.acquirePlaceCode
        val acquisitionData = mainResultData.acquisitionCode

        val intent = Intent(this, ListActivity::class.java)
//        .apply {
//            putExtra(ListActivity.ACQUIRE_PLACE, acquirePlaceData)
//            putExtra(ListActivity.ACQUISITION, acquisitionData)
//        }
        startActivity(intent)
    }

}
