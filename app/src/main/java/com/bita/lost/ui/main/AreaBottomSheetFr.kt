package com.bita.lost.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bita.lost.R
import com.bita.lost.databinding.AreaFrBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AreaBottomSheetFr : BottomSheetDialogFragment() {

    private lateinit var bb: AreaFrBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.area_fr, container, false)
        return bb.root
    }
}