package com.example.picsumgallery.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.ListItemGalleryBinding

// 230418
class GalleryAdapter(
    private var galleryItems: List<Picsum> = emptyList(),
    private val onClick: (Int) -> Unit,
) :
    RecyclerView.Adapter<GalleryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
        val binding = ListItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        val galleryItem = galleryItems[position]
        holder.bind(galleryItem)
        holder.binding.root.setOnClickListener {
            onClick(galleryItem.id)
            Log.d("GalleryAdapter", "clicked #${galleryItem.id}")
        }
    }

    override fun getItemCount(): Int = galleryItems.size
    fun fetchData(galleryItems: List<Picsum>) {
        this.galleryItems = galleryItems
        notifyDataSetChanged()
    }
}
