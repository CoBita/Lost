package com.bita.lost.ui.list

import android.os.Bundle
import android.transition.Explode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.and.base.log.Log
import com.bita.lost.R
import com.bita.lost.base.LFragment
import com.bita.lost.databinding.ListFrBinding
import com.bita.lost.repo.data.AreaCode
import com.bita.lost.repo.data.ProductCode
import com.google.android.gms.ads.AdRequest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment : LFragment() {
    override val vm: ListViewModel by sharedViewModel()
    private lateinit var binding: ListFrBinding
    private lateinit var area: AreaCode
    private lateinit var product: ProductCode
    private lateinit var startDate: String
    private lateinit var endDate: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fr, container, false)
        return binding.root
    }

    override fun onLoadOnce() {
        super.onLoadOnce()

        if (::area.isInitialized && ::product.isInitialized && ::startDate.isInitialized && ::endDate.isInitialized) {
            vm.init(area, product, startDate, endDate)
            binding.vm = vm
            vm.getFirstLostList()
            // todo 테스트용 이후 삭제 필요
//            vm.습득물조회fromDummy(activity?.raw2String(R.raw.dummy_lost_list))
            binding.header.setOnClickListener {
                ListHeaderDialog.newInstance(vm.area.get(), vm.product.get(), vm.displayPeriod.get()).show(childFragmentManager, null)
            }
            initializeAds()
        } else (activity as? ListAct)?.showError()
    }

    private fun initializeAds() {
        binding.adView.loadAd(AdRequest.Builder().build())
    }

    fun changeParameter(param: Any) {
        when (param) {
            is AreaCode -> vm.searchAgain(pArea = param)
            is ProductCode -> vm.searchAgain(pProduct = param)
            else -> Log.w("param = $param")
        }
    }

    companion object {
        fun newInstance(pArea: AreaCode, pProduct: ProductCode, pStart: String, pEnd: String): ListFragment {
            return ListFragment().apply {
                area = pArea
                product = pProduct
                startDate = pStart
                endDate = pEnd
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