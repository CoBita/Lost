package com.bita.lost.ui.list

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.and.base.log.Log
import com.bita.lost.R
import com.bita.lost.base.LActivity
import com.bita.lost.databinding.ListActBinding
import com.bita.lost.repo.AcquirePlaceCode
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : LActivity() {
    override val vm: ListViewModel by viewModel()
    private val binding by lazy { DataBindingUtil.setContentView<ListActBinding>(this, R.layout.list_act) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = vm
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
//        vm.result.observe(this, Observer { Log.w(it.service.items.joinToString("\n")) })
    }

    override fun onLoad() {
        super.onLoad()
        vm.분실물조회(1, 10, "핸드폰", AcquirePlaceCode.버스.code, "")
    }
}