package com.bita.lost.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.Slide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bita.lost.R
import com.bita.lost.base.LFragment
import com.bita.lost.databinding.DetailFrBinding
import com.google.android.gms.ads.AdRequest
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

        if (id != null && seq != null) {
            vm.습득물상세조회(id, seq)
        }
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        binding.vm = vm
        val title = arguments?.getString(TITLE)
        if (title != null) {
            vm.title.set(title)
        }
        binding.adView.loadAd(AdRequest.Builder().build())
        vm.goTel.observe(this, Observer { it?.let { num -> goTel(num) } })
        vm.finishAlert.observe(this, Observer {
            it?.let { message ->
                showDialog(message = message, positiveButtonText = "확인", positiveListener = { _, _ ->
                    activity?.onBackPressed()
                })
            }
        })

        vm.findPlace.observe(this, Observer {
            it?.let { place ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/link/search/$place")).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(intent)
            }
        })
    }

    private fun goTel(num: String) {
        val telIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$num"))
        startActivity(telIntent)
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
    companion object {
        fun newInstance(id: String, seq: Int, title: String): DetailFragment {
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