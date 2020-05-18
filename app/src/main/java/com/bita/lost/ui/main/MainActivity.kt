package com.bita.lost.ui.main

import android.os.Bundle
import com.bita.lost.R
import com.bita.lost.base.LActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : LActivity() {
    override val vm: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onLoad() {
        super.onLoad()
        vm.분실물조회(1, 5, "핸드폰", "b1", "")
    }
}
