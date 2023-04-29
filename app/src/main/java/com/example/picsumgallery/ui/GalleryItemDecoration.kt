package com.example.picsumgallery.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GalleryItemDecoration(
    private val offset: Int = 10,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val count = state.itemCount
        if (parent.layoutManager !is GridLayoutManager) {
            return
        }
        val spanIndex = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex
        val spanCount = (parent.layoutManager as GridLayoutManager).spanCount

        if (position < spanCount) {
            outRect.bottom = offset
        } else if (position >= (count - spanCount)) {
            outRect.top = offset
        } else {
            outRect.top = offset
            outRect.bottom = offset
        }

        if (spanIndex == 0) {
            outRect.right = offset
        } else if (spanIndex == spanCount - 1) {
            outRect.left = offset
        } else {
            outRect.left = offset
            outRect.right = offset
        }
    }
}
