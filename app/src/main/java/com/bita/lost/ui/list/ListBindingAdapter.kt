package com.bita.lost.ui.list

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.and.base.log.Log
import com.bita.lost.R
import com.bumptech.glide.Glide

object ListBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:items", "app:getNextData")
    fun setAdapter(v: RecyclerView, data: ArrayList<Any>, function: () -> Unit) {
        v.adapter?.let { it as? ListAdapter }?.set(data)
                ?: run {
                    val adapter = ListAdapter { id, seq -> v.context.let { it as? ListAct }?.showDetail(id, seq) }
                    adapter.set(data)
                    v.adapter = adapter
                }
        v.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    Log.w("스크롤 최하단 도달")
                    function()
                }
            }
        })
        v.scheduleLayoutAnimation()
    }

    @JvmStatic
    @BindingAdapter("app:src")
    fun setSrc(v: ImageView, drawable: Any) {
        Glide.with(v.context).load(drawable).into(v)
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
    fun noSpace(v: TextView, string: String) {
        v.text = string.replace(" ", "\u00A0").replace("-", "\u2011").replace("/", "\u2215")
    }
}