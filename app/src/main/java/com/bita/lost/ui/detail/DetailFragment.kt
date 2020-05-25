package com.bita.lost.ui.detail

import android.os.Bundle
import android.transition.Slide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        // TODO 경찰청 API로 교체 후 해당 내용 주석 해제 필요
//        arguments?.getString(ID)?.let { id -> vm.분실물상세조회(id) }
    }

    companion object {
        fun newInstance(id: String): DetailFragment {
            val bundle = Bundle().apply { putString(ID, id) }
            return DetailFragment().apply {
                arguments = bundle
                enterTransition = Slide()
                reenterTransition = null
            }
        }

        const val ID = "ID"
    }

}