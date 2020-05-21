package com.bita.lost.ui.list

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.view.Window
import com.bita.lost.R
import com.bita.lost.base.LActivity
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
        replace(false)
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

    fun replace(isDetail: Boolean, id: String? = null) {
        val target = if (isDetail) DetailFragment().apply {
            val bundle = Bundle()
            bundle.putString(DetailFragment.ID, id)
            arguments = bundle
            enterTransition = Slide()
            reenterTransition = null
        } else listFr


        val transaction = supportFragmentManager.beginTransaction().replace(R.id.container, target)
        if (isDetail) transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        const val EXTRA_ACQUIRE_PLACE = "ACQUIRE_PLACE"
        const val EXTRA_ACQUISITION = "ACQUISITION"
        const val EXTRA_SEARCH = "EXTRA_SEARCH"
    }
}