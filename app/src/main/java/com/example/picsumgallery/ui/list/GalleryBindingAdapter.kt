package com.example.picsumgallery.ui.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.ui.list.adapter.GalleryAdapter

@BindingAdapter("bind:galleryList")
fun RecyclerView.setGalleryList(list: List<Picsum>?) {
    (adapter as? GalleryAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:onItemClickListener")
fun RecyclerView.setGalleryListItemClickListener(listener: GalleryAdapter.OnItemClickListener?) {
    (adapter as? GalleryAdapter)?.onClick = listener
}
