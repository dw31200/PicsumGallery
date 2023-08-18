package com.example.picsumgallery.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.ui.databinding.ListItemGalleryBinding
import com.example.picsumgallery.ui.list.GalleryNavigation
import com.example.picsumgallery.ui.model.PicsumUiModel

class GalleryHolder(
    private val binding: ListItemGalleryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: PicsumUiModel?, galleryNavigation: GalleryNavigation?) {
        with(binding) {
            data = galleryItem
            galleryImage.setOnClickListener {
                if (galleryItem != null) {
                    galleryNavigation?.navigateToDetail(galleryItem.id)
                }
            }
            executePendingBindings()
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): GalleryHolder {
            val binding =
                ListItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return GalleryHolder(binding)
        }
    }
}
