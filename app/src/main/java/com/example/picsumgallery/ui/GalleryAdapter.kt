package com.example.picsumgallery.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.ListItemGalleryBinding

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
        holder.bind(position)
        holder.binding.root.setOnClickListener {
            onClick(position)
            Log.d("GalleryAdapter", "clicked #$position")
        }
    }

    override fun getItemCount(): Int = galleryItems.size
    fun fetchData(galleryItems: List<Picsum>) {
        this.galleryItems = galleryItems
        notifyDataSetChanged()
    }
}
