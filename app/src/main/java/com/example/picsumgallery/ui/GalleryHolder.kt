package com.example.picsumgallery.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.ListItemGalleryBinding
import com.squareup.picasso.Picasso

class GalleryHolder(
    val binding: ListItemGalleryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(galleryItem: Picsum) {
        binding.galleryAuthor.text = galleryItem.author
        Picasso
            .get()
            .load(galleryItem.url)
            .into(binding.galleryImage)
    }
}
