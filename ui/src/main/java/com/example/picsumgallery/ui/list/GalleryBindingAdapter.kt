package com.example.picsumgallery.ui.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.ui.like.LikeNavigation
import com.example.picsumgallery.ui.like.adpater.LikeAdapter
import com.example.picsumgallery.ui.list.adapter.GalleryAdapter
import com.example.picsumgallery.ui.model.PicsumUiModel

@BindingAdapter("bind:galleryList")
fun RecyclerView.setGalleryList(list: List<PicsumUiModel>?) {
    (adapter as? GalleryAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:galleryLikeList")
fun RecyclerView.setGalleryLikeList(list: List<PicsumUiModel>?) {
    (adapter as? LikeAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:onItemClickListener")
fun RecyclerView.setGalleryListItemClickListener(galleryNavigation: GalleryNavigation) {
    (adapter as? GalleryAdapter)?.galleryNavigation = galleryNavigation
}

@BindingAdapter("bind:onLikeItemClickListener")
fun RecyclerView.setLikeItemClickListener(likeNavigation: LikeNavigation) {
    (adapter as? LikeAdapter)?.likeNavigation = likeNavigation
}
