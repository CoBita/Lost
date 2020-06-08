package com.bita.lost.ui.detail

import android.os.Bundle
import android.transition.Slide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bita.lost.R
import com.bita.lost.base.LFragment
import com.bita.lost.databinding.DetailFrBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : LFragment() {
    override val vm: DetailViewModel by viewModel()
    private lateinit var binding: DetailFrBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fr, container, false)
        return binding.root
    }

    override fun onParseExtra() {
        super.onParseExtra()
        val id = arguments?.getString(ID)
        val seq = arguments?.getInt(SEQ)
        val title = arguments?.getString(TITLE)
        Toast.makeText(activity, title, Toast.LENGTH_LONG).show()
        if (id != null && seq != null) {
            vm.습득물상세조회(id, seq)
        }
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        binding.vm = vm
    }

    companion object {
        fun newInstance(id: String, seq: Int, title : String): DetailFragment {
            val bundle = Bundle().apply {
                putString(ID, id)
                putInt(SEQ, seq)
                putString(TITLE, title)
            }
            return DetailFragment().apply {
                arguments = bundle
                enterTransition = Slide()
                reenterTransition = null
            }
        }

        const val ID = "ID"
        const val SEQ = "SEQ"
        const val TITLE = "TITLE"
    }

}