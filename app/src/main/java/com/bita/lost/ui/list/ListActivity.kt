package com.bita.lost.ui.list

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import com.bita.lost.R
import com.bita.lost.base.LActivity
import com.bita.lost.ui.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : LActivity() {
    override val vm: ListViewModel by viewModel()
    private val listFr = ListFragment().apply { exitTransition = Explode() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_act)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        replace()
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

    fun replace() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, listFr)
            commit()
        }
    }

    fun detailReplace(id: String) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, DetailFragment.newInstance(id))
            addToBackStack(null)
            commit()
        }
    }

    companion object {
        const val EXTRA_ACQUIRE_PLACE = "ACQUIRE_PLACE"
        const val EXTRA_ACQUISITION = "ACQUISITION"
    }
}