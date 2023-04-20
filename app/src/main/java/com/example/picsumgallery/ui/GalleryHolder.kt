package com.example.picsumgallery.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.ListItemGalleryBinding
import com.squareup.picasso.Picasso

class GalleryHolder(val binding: ListItemGalleryBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var galleryItem: Picsum

    fun bind(galleryItem: Picsum) {
        this.galleryItem = galleryItem
        binding.galleryAuthor.text = this.galleryItem.author
        Picasso.get()
            .load(galleryItem.url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.galleryImage)
    }
}
