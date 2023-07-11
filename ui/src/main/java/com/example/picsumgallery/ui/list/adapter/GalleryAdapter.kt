package com.example.picsumgallery.ui.list.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.ui.list.GalleryNavigation
import com.example.picsumgallery.ui.model.PicsumUiModel

class GalleryAdapter(
    private val galleryItems: MutableList<PicsumUiModel> = mutableListOf(),
) : RecyclerView.Adapter<GalleryHolder>() {
    var galleryNavigation: GalleryNavigation? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder =
        GalleryHolder(parent)

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        holder.bind(galleryItems[position], galleryNavigation)
    }

    override fun getItemCount(): Int = galleryItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(galleryItems: List<PicsumUiModel>) {
        this.galleryItems.clear()
        this.galleryItems.addAll(galleryItems)
        notifyDataSetChanged()
    }
}
