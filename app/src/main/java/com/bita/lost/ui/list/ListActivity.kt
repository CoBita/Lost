package com.bita.lost.ui.list

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import com.airbnb.lottie.LottieAnimationView
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
        vm.init("핸드폰", AcquirePlaceCode.버스.code, "")
    }

    override fun onLoad() {
        super.onLoad()
        vm.getLostList()
    }
}