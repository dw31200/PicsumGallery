package com.example.picsumgallery.ui.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.data.Picsum

class GalleryAdapter(
    private val galleryItems: MutableList<Picsum> = mutableListOf(),
    private val onClick: (Int) -> Unit,
) : RecyclerView.Adapter<GalleryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder =
        GalleryHolder(parent)

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        holder.bind(galleryItems[position], onClick)
    }

    override fun getItemCount(): Int = galleryItems.size
    fun fetchData(galleryItems: List<Picsum>) {
        this.galleryItems.clear()
        this.galleryItems.addAll(galleryItems)
        notifyDataSetChanged()
    }

    fun addData(galleryItems: List<Picsum>) {
        this.galleryItems.addAll(galleryItems)
        notifyDataSetChanged()
    }
}
