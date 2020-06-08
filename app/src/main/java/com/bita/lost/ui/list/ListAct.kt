package com.bita.lost.ui.list

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.and.base.common.EventObserver
import com.bita.lost.R
import com.bita.lost.base.LActivity
import com.bita.lost.repo.data.AreaCode
import com.bita.lost.repo.data.ProductCode
import com.bita.lost.ui.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListAct : LActivity() {
    override val vm: ListViewModel by viewModel()
    private lateinit var listFr: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_act)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        vm.backPressed.observe(this, EventObserver { onBackPressed() })
        if (::listFr.isInitialized) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, listFr)
                commit()
            }
        } else showError()
    }

    override fun onParseExtra() {
        super.onParseExtra()
        val area = intent.getSerializableExtra(AREA) ?: AreaCode.전체지역
        val product = intent.getSerializableExtra(PRODUCT) ?: ProductCode.모든습득물
        val startDate = intent.getStringExtra(START_DATE) ?: ""
        val endDate = intent.getStringExtra(END_DATE) ?: ""

        if (area is AreaCode && product is ProductCode) {
            listFr = ListFragment.newInstance(area, product, startDate, endDate)
        }
    }

    fun showDetail(id: String, seq: Int, title : String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, DetailFragment.newInstance(id, seq, title))
            addToBackStack(null)
            commit()
        }
    }

    override fun createProgress(): Dialog = Dialog(mContext).apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(false)
        window?.apply {
            setContentView(R.layout.list_loading)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun showError() {
        showDialog("", "잃어버리신 장소 및 물건의 이름을 다시 한 번 입력해주세요~", positiveButtonText = "네!", positiveListener = { _, _ -> finish() })
    }

    companion object {
        // Parameter
        const val AREA = "AREA"
        const val PRODUCT = "PRODUCT"
        const val START_DATE = "START_DATE"
        const val END_DATE = "END_DATE"
    }
}