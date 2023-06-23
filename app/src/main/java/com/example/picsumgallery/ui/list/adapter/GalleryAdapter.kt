package com.example.picsumgallery.ui.list.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.data.model.Picsum

class GalleryAdapter(
    private val galleryItems: MutableList<Picsum> = mutableListOf(),
) : RecyclerView.Adapter<GalleryHolder>() {
    interface OnItemClickListener {
        // TODO GalleryFragment 의 navigationToDetail 가 구현한 함수가 되는게 맞나요? 이름이 다른데 override 되는것 같은데, 원리를 다시 설명 해주세요.
        fun onClick(galleryId: Int)
    }

    var onClick: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder =
        GalleryHolder(parent)

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        holder.bind(galleryItems[position], onClick)
    }

    override fun getItemCount(): Int = galleryItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun fetchData(galleryItems: List<Picsum>) {
        this.galleryItems.clear()
        this.galleryItems.addAll(galleryItems)
        notifyDataSetChanged()
    }
}
