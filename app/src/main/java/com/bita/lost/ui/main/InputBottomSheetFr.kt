package com.bita.lost.ui.main

import android.os.Bundle
import android.view.*
import com.and.base.common.showKeyboard
import com.bita.lost.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.input_bottom_fr.*

class InputBottomSheetFr : BottomSheetDialogFragment() {

    var onInput: ((input: String) -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return inflater.inflate(R.layout.input_bottom_fr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        close.setOnClickListener { dismiss() }
        search.setOnClickListener { finish() }
        arguments?.let {
            it.getString(EXTRA_TITLE)?.let { extraTitle ->
                title.text = extraTitle
            }
        }


        input.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                input.viewTreeObserver.removeOnGlobalLayoutListener(this)
                input.showKeyboard()
            }
        })
    }


    private fun finish() {
        val inputText = input.editableText.toString()
        onInput?.invoke(inputText)
        dismiss()
    }


    companion object {
        const val EXTRA_TITLE = "EXTRA_TITLE"

        fun newInstance(title: String, input: ((input: String) -> Unit)): InputBottomSheetFr {
            val bundle = Bundle().apply {
                putString(EXTRA_TITLE, title)
            }
            val bottomSheetFr = InputBottomSheetFr().apply {
                onInput = input
                arguments = bundle
            }
            return bottomSheetFr
        }

    }
}