package com.bita.lost.ui.list

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.and.base.log.Log
import com.bita.lost.R

object ListBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:items", "app:getNextData")
    fun setAdapter(v: RecyclerView, data: ArrayList<Any>, function: () -> Unit) {
        v.adapter?.let { it as? ListAdapter }?.set(data)
                ?: run {
                    val adapter = ListAdapter { id, seq, title -> v.context.let { it as? ListAct }?.showDetail(id, seq, title) }
                    adapter.set(data)
                    v.adapter = adapter
                }
        v.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            val lm = v.layoutManager as? LinearLayoutManager
            var lastVisibleIndex = -1
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                lm?.let {
                    val currentLast = it.findLastVisibleItemPosition()
                    if (currentLast > lastVisibleIndex) {
                        lastVisibleIndex = currentLast
                        (v.adapter as? ListAdapter)?.setLastVisibleIndex(currentLast)
                    }
                }
                if (!recyclerView.canScrollVertically(1)) {
                    function()
                }
            }
        })
        v.scheduleLayoutAnimation()
    }


    @JvmStatic
    @BindingAdapter("app:translationY")
    fun translationY(v: View, flag: Boolean) {
        if (!flag) {
            v.alpha = 0f
        } else {
            v.alpha = 1f
            v.animation = AnimationUtils.loadAnimation(v.context, R.anim.appear_anim)
        }
    }

    @JvmStatic
    @BindingAdapter("app:noBreakText")
    fun noSpace(v: TextView, string: String?) {
        string?.let {
            v.text = it.replace(" ", "\u00A0").replace("-", "\u2011").replace("/", "\u2215")
        }

    }
}