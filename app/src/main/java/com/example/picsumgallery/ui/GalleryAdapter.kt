package com.example.picsumgallery.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.ListItemGalleryBinding

// 230418
class GalleryAdapter(var galleryItems: List<Picsum> = emptyList()) :
    RecyclerView.Adapter<GalleryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
        val binding = ListItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        val galleryItem = galleryItems[position]
        holder.bind(galleryItem)
        holder.binding.root.setOnClickListener {
            Log.d("GalleryAdapter", "clicked #${galleryItem.id}")
        }
    }

    override fun getItemCount(): Int = galleryItems.size
    fun fetchData() {
        notifyDataSetChanged()
    }
}
