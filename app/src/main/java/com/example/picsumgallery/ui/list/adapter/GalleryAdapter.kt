package com.example.picsumgallery.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.ListItemGalleryBinding
import timber.log.Timber

class GalleryAdapter(
    private val galleryItems: MutableList<Picsum> = mutableListOf(),
    private val onClick: (Int) -> Unit,
) : RecyclerView.Adapter<GalleryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
        val binding = ListItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        holder.bind(galleryItems[position])
        holder.binding.root.setOnClickListener {
            onClick(galleryItems[position].id)
            Timber.d("clicked #${galleryItems[position].id}")
        }
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
