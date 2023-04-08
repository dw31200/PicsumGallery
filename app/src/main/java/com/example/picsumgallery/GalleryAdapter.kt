package com.example.picsumgallery

import android.graphics.drawable.Drawable
import android.os.HandlerThread
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView

class GalleryAdapter(var galleryItems: List<Picsum>): RecyclerView.Adapter<GalleryAdapter.GalleryHolder>(){

    class GalleryHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var galleryItem: Picsum

        private val galleryImageView: ImageView = itemView.findViewById(R.id.gallery_image)
        private val galleryAuthorTextView: TextView = itemView.findViewById(R.id.gallery_author)
        private val bindDrawable: (Drawable) -> Unit = galleryImageView::setImageDrawable

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(galleryItem: Picsum) {
            this.galleryItem = galleryItem
            galleryAuthorTextView.text = this.galleryItem.author
        }

        override fun onClick(view: View) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.GalleryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_gallery, parent, false)
        return GalleryHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        val galleryItem = galleryItems[position]
        holder.bind(galleryItem)
    }

    override fun getItemCount(): Int = galleryItems.size

}
