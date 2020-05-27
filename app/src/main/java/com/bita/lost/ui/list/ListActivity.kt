package com.bita.lost.ui.list

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.bita.lost.R
import com.bita.lost.base.LActivity
import com.bita.lost.repo.data.AcquirePlaceCode
import com.bita.lost.repo.data.AcquisitionCode
import com.bita.lost.ui.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : LActivity() {
    override val vm: ListViewModel by viewModel()
    private lateinit var listFr: ListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_act)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        if (::listFr.isInitialized) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, listFr)
                commit()
            }
        } else
            showDialog("", "잃어버리신 장소 및 물건의 이름을 다시 한 번 입력해주세요~", positiveButtonText = "네!", positiveListener = { _, _ -> finish() })

    }

    override fun onParseExtra() {
        super.onParseExtra()
        val place = intent.getSerializableExtra(ACQUIRE_PLACE)
        val name = intent.getSerializableExtra(ACQUISITION)

        if (place is AcquirePlaceCode && name is AcquisitionCode) {
            listFr = ListFragment.newInstance(place, name)
        }
    }

    fun showDetail(id: String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, DetailFragment.newInstance(id))
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

    companion object {
        const val ACQUIRE_PLACE = "ACQUIRE_PLACE"
        const val ACQUISITION = "ACQUISITION"
    }
}