package com.example.picsumgallery.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GalleryItemDecoration(
    private val width: Int = 10,
    private val height: Int = 10,
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

        when {
            position < spanCount -> {
                outRect.bottom = height / 2
            }
            position >= (count - spanCount) -> {
                outRect.top = height / 2
            }
            else -> {
                outRect.top = height / 2
                outRect.bottom = height / 2
            }
        }

        when (spanIndex) {
            0 -> {
                outRect.right = width / 2
            }
            spanCount - 1 -> {
                outRect.left = width / 2
            }
            else -> {
                outRect.left = width / 2
                outRect.right = width / 2
            }
        }
    }
}
