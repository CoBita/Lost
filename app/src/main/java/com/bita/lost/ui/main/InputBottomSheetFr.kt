package com.bita.lost.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bita.lost.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.input_bottom_fr.*

class InputBottomSheetFr : BottomSheetDialogFragment() {

    var onInput: ((input: String) -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.input_bottom_fr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        close.setOnClickListener { dismiss() }
        search.setOnClickListener { finish() }
    }


    private fun finish() {
        val inputText = input.editableText.toString()
        onInput?.invoke(inputText)
        dismiss()
    }


    companion object {
        fun newInstance(input: ((input: String) -> Unit)): InputBottomSheetFr {
            val bottomSheetFr = InputBottomSheetFr().apply {
                onInput = input
            }
            return bottomSheetFr
        }

    }
}