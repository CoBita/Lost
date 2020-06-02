package com.bita.lost.ui.list

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.transition.Explode
import android.view.*
import androidx.databinding.DataBindingUtil
import com.bita.lost.R
import com.bita.lost.base.LFragment
import com.bita.lost.common.raw2String
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
        // todo 전문 속도 너무 느려서 dummy 데이터로 처리 중 이후 변경 필요
//        vm.최초습득물조회()
        vm.습득물조회fromDummy(activity?.raw2String(R.raw.dummy_lost_list))
        binding.header.setOnClickListener {
            activity?.let { context ->
                Dialog(context).apply {
                    requestWindowFeature(Window.FEATURE_NO_TITLE)
                    window?.apply {
                        setContentView(R.layout.header_expand_dialog)
                        setGravity(Gravity.TOP)
                        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        val params = attributes.apply {
                            width = WindowManager.LayoutParams.MATCH_PARENT
                            windowAnimations = R.style.AnimationPopupStyle
                        }
                        attributes = params
                    }
                }.show()
            }

        }
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