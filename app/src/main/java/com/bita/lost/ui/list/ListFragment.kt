package com.bita.lost.ui.list

import android.os.Bundle
import android.transition.Explode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bita.lost.R
import com.bita.lost.base.LFragment
import com.bita.lost.databinding.ListFrBinding
import com.bita.lost.repo.data.AreaCode
import com.bita.lost.repo.data.ProductCode
import com.google.android.gms.ads.AdRequest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment private constructor() : LFragment() {
    override val vm: ListViewModel by sharedViewModel()
    private lateinit var binding: ListFrBinding
    private lateinit var nFdLctCd: AreaCode
    private lateinit var prdtClCd01: ProductCode
    private lateinit var startYmd: String
    private lateinit var endYmd: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fr, container, false)
        return binding.root
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        initializeAds()

        vm.init(nFdLctCd, prdtClCd01, startYmd, endYmd)
        binding.vm = vm
        vm.getFirstLostList()
    }

    private fun initializeAds() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    companion object {
        fun newInstance(nFdLctCd: AreaCode,prdtClCd01: ProductCode, startYmd: String, endYmd: String): ListFragment {
            return ListFragment().apply {
                this.nFdLctCd = nFdLctCd
                this.prdtClCd01 = prdtClCd01
                this.startYmd = startYmd
                this.endYmd = endYmd
                exitTransition = Explode()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.adView.pause()
    }

    override fun onResume() {
        super.onResume()
        binding.adView.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.adView.destroy()
    }
}