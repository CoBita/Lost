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
import com.bita.lost.repo.data.AcquirePlaceCode
import com.bita.lost.repo.data.AcquisitionCode
import com.google.android.gms.ads.AdRequest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment private constructor() : LFragment() {
    override val vm: ListViewModel by sharedViewModel()
    private lateinit var binding: ListFrBinding
    private lateinit var place: AcquirePlaceCode
    private lateinit var name: AcquisitionCode

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fr, container, false)
        return binding.root
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        initializeAds()

        vm.init(place, name)
        binding.vm = vm
        vm.getFirstLostList()
    }

    private fun initializeAds(){
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    companion object {
        fun newInstance(place: AcquirePlaceCode, name: AcquisitionCode): ListFragment {
            return ListFragment().apply {
                this.place = place
                this.name = name
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