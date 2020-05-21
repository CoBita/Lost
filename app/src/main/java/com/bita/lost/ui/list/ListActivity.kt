package com.bita.lost.ui.list

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.bita.lost.R
import com.bita.lost.base.LActivity
import com.bita.lost.databinding.ListActBinding
import com.bita.lost.repo.data.AcquirePlaceCode
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
    }

    override fun createProgress(): Dialog {
        val dialog = Dialog(mContext)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.window?.apply {
            setContentView(R.layout.list_loading)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        return dialog
    }

    override fun onParseExtra() {
        super.onParseExtra()
        // todo 메인에서 넘어온 값으로 변경 필요
        vm.init("핸드폰", AcquirePlaceCode.지하철1호선에서4호선, "")
    }

    override fun onLoad() {
        super.onLoad()
        vm.getLostList()
    }

    companion object {
        const val EXTRA_ACQUIRE_PLACE = "ACQUIRE_PLACE"
        const val EXTRA_ACQUISITION = "ACQUISITION"
        const val EXTRA_SEARCH = "EXTRA_SEARCH"
    }
}