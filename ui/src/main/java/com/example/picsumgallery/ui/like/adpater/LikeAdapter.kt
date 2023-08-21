package com.example.picsumgallery.ui.like.adpater

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.ui.like.LikeNavigation
import com.example.picsumgallery.ui.model.PicsumUiModel

class LikeAdapter(
    private val galleryItems: MutableList<PicsumUiModel> = mutableListOf(),
) : RecyclerView.Adapter<LikeHolder>() {
    var likeNavigation: LikeNavigation? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeHolder =
        LikeHolder(parent)

    override fun onBindViewHolder(holder: LikeHolder, position: Int) {
        holder.bind(galleryItems[position], likeNavigation)
    }

    override fun getItemCount(): Int = galleryItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(galleryItems: List<PicsumUiModel>) {
        this.galleryItems.clear()
        this.galleryItems.addAll(galleryItems)
        notifyDataSetChanged()
    }
}
