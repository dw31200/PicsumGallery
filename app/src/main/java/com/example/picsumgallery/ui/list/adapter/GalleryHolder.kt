package com.example.picsumgallery.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.ListItemGalleryBinding

class GalleryHolder(
    private val binding: ListItemGalleryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: Picsum, onClick: (Int) -> Unit) {
        with(binding) {
            galleryAuthor.text = galleryItem.author
            Glide
                .with(root)
                .load(galleryItem.url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(galleryImage)
            galleryImage.setOnClickListener {
                onClick(galleryItem.id)
            }
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
