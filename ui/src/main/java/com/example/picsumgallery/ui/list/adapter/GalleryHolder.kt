package com.example.picsumgallery.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.ui.databinding.ListItemGalleryBinding
import com.example.picsumgallery.ui.list.GalleryNavigation
import com.example.picsumgallery.ui.model.PicsumUiModel

class GalleryHolder(
    private val binding: ListItemGalleryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    private var lifecycleOwner: LifecycleOwner? = null

    fun bind(galleryItem: PicsumUiModel, galleryNavigation: GalleryNavigation?) {
        with(binding) {
            data = galleryItem
            galleryImage.setOnClickListener {
                galleryNavigation?.navigateToDetail(galleryItem.id)
            }
            executePendingBindings()
        }
    }

    init {
        itemView.doOnAttach {
            lifecycleOwner = itemView.findViewTreeLifecycleOwner()
        }
        itemView.doOnDetach {
            lifecycleOwner = null
        }
        binding.lifecycleOwner = lifecycleOwner
    }

    companion object {
        operator fun invoke(parent: ViewGroup): GalleryHolder {
            val binding =
                ListItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return GalleryHolder(binding)
        }
    }
}
