package com.example.picsumgallery.ui.list

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.ui.list.adapter.GalleryAdapter

@BindingAdapter("bind:visible")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:galleryList")
fun RecyclerView.setGalleryList(list: List<Picsum>?) {
    (adapter as? GalleryAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:onItemClickListener")
fun RecyclerView.setGalleryListItemClickListener(listener: GalleryAdapter.onItemClickListener?) {
    (adapter as? GalleryAdapter)?.onClick = listener
}
