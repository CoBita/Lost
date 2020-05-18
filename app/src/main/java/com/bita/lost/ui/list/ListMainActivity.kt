package com.bita.lost.ui.list

import android.os.Bundle
import android.os.PersistableBundle
import com.bita.lost.R
import com.bita.lost.base.LActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMainActivity : LActivity() {
    override val vm: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.list_act)
    }
}