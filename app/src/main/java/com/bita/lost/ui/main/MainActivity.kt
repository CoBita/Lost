package com.bita.lost.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.bita.lost.R
import com.bita.lost.base.LActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : LActivity() {
    override val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        replace()

        vm.selectAcquirePlaceData.observe(this, Observer {
            it?.let {

            }
        })
    }


    private fun replace() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, AcquirePlaceFr.newInstance(), ACQUIRE_PLACE_TAG)
                .commit()
    }

    companion object {
        const val ACQUIRE_PLACE_TAG = "AcquirePlace"

    }

}
