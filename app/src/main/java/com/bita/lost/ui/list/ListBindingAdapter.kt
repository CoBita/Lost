package com.bita.lost.ui.list

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bita.lost.R
import com.bita.lost.repo.data.LostItem
import com.bumptech.glide.Glide

object ListBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:items", "app:has_next", "app:get_next_data", "app:is_load_finish")
    fun setAdapter(v: RecyclerView, rawData: ArrayList<LostItem>, hasNext: Boolean, function: () -> Unit, isLoadFinish: Boolean) {
        if (!isLoadFinish) return

        val data = arrayListOf<LostItem?>().apply {
            addAll(rawData)
            if (hasNext && isNotEmpty()) add(null)
            if (this.isEmpty()) add(null)
        }

        v.adapter?.let { it as? ListAdapter }?.set(data)
                ?: run {
                    val adapter = ListAdapter({ id, seq -> v.context.let { it as? ListActivity }?.showDetail(id, seq) }, function)
                    adapter.set(data)
                    v.adapter = adapter
                }
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
    fun noSpace(v : TextView, string : String){
        v.text = string.replace(" ", "\u00A0").replace("-", "\u2011").replace("/", "\u2215")
    }
}