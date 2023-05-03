package com.example.picsumgallery.ui.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.ListItemGalleryBinding

class GalleryHolder(
    val binding: ListItemGalleryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: Picsum) {
        with(binding) {
            galleryAuthor.text = galleryItem.author
            Glide
                .with(root)
                .load(galleryItem.url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(galleryImage)
        }
    }
}
