package com.example.picsumgallery.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum

class GalleryAdapter(var galleryItems: List<Picsum>, private val onClick: (Picsum) -> Unit) : RecyclerView.Adapter<GalleryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_gallery, parent, false)
        return GalleryHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        val galleryItem = galleryItems[position]
        holder.bindGalleryItem(galleryItem)
    }

    override fun getItemCount(): Int = galleryItems.size
}
