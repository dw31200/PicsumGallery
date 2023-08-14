package com.example.picsumgallery.ui.like.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.ui.databinding.ListLikeItemGalleryBinding
import com.example.picsumgallery.ui.like.LikeNavigation
import com.example.picsumgallery.ui.model.PicsumUiModel

class LikeHolder(
    private val binding: ListLikeItemGalleryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: PicsumUiModel, likeNavigation: LikeNavigation?) {
        with(binding) {
            data = galleryItem
            galleryImage.setOnClickListener {
                likeNavigation?.navigateToDetail(galleryItem.id)
            }
            executePendingBindings()
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): LikeHolder {
            val binding =
                ListLikeItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LikeHolder(binding)
        }
    }
}
