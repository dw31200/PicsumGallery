package com.example.picsumgallery.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum
import com.squareup.picasso.Picasso

class GalleryHolder(view: View, val onClick: (Picsum) -> Unit) : RecyclerView.ViewHolder(view) {
    private lateinit var galleryItem: Picsum
    private val galleryImageView: ImageView = view.findViewById(R.id.gallery_image)
    private val galleryAuthorTextView: TextView = view.findViewById(R.id.gallery_author)

    init {
        view.setOnClickListener {
            galleryItem?.let {
                onClick(it)
            }
        }
    }

    fun bindGalleryItem(galleryItem: Picsum) {
        this.galleryItem = galleryItem
        galleryAuthorTextView.text = this.galleryItem.author
        Picasso.get()
            .load(galleryItem.url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(galleryImageView)
    }
}
