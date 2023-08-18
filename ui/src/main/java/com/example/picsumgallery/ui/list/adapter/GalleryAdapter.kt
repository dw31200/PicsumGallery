package com.example.picsumgallery.ui.list.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.picsumgallery.ui.list.GalleryNavigation
import com.example.picsumgallery.ui.model.PicsumUiModel

class GalleryAdapter : PagingDataAdapter<PicsumUiModel, GalleryHolder>(GalleryDiffUtil) {
    var galleryNavigation: GalleryNavigation? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder =
        GalleryHolder(parent)

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        holder.bind(getItem(position), galleryNavigation)
    }

    object GalleryDiffUtil : DiffUtil.ItemCallback<PicsumUiModel>() {
        override fun areItemsTheSame(oldItem: PicsumUiModel, newItem: PicsumUiModel): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PicsumUiModel, newItem: PicsumUiModel): Boolean {
            return oldItem == newItem
        }
    }
}
