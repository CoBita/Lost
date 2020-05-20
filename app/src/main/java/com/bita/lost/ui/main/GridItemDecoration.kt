package com.bita.lost.ui.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.and.base.common.dp2px

class GridItemDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val context = view.context
        val dp = context.dp2px(offset.toFloat())
        outRect.set(dp, dp, dp, dp)
    }

}