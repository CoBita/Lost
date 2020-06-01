package com.bita.lost.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.and.base.log.Log
import com.bita.lost.R
import com.bita.lost.base.LActivity
import com.bita.lost.databinding.SearchActBinding
import kotlinx.android.synthetic.main.search_act.*
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

        color.setOnClickListener {
            ColorPickFr.newInstance {
                Log.i(it)
                color.setText(it.name)
            }.show(supportFragmentManager, COLOR_PICK_TAG)
        }

        area.setOnClickListener {
            AreaFr.newInstance {
                Log.i(it)
                area.setText(it.name)
            }.show(supportFragmentManager, AREA_PICK_TAG)
        }
    }

    companion object {
        const val AREA_PICK_TAG = "areaPick"
        const val COLOR_PICK_TAG = "colorPick"
    }
}