package com.bita.lost.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bita.lost.R
import com.bita.lost.base.LActivity
import com.bita.lost.databinding.ActivityMainBinding
import com.bita.lost.repo.AcquirePlaceCode
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
        replace()

        vm.selectAcquirePlaceData.observe(this, Observer {
            it?.let {

            }
        })

        bb.dummy.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
    }


    private fun replace() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, AcquisitionFr.newInstance(), ACQUIRE_PLACE_TAG)
                .commit()
    }

    companion object {
        const val ACQUIRE_PLACE_TAG = "AcquirePlace"
        const val ACQUISITION_TAG = "Acquisition"
    }

}
