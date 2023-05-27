package com.example.picsumgallery.ui.list.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.picsumgallery.data.Picsum

class GalleryHolderViewModel {
    private val _galleryItem = MutableLiveData<Picsum>()
    val galleryItem: LiveData<Picsum>
        get() = _galleryItem
//    fun bind(galleryItem: Picsum, onClick: GalleryAdapter.onItemClickListener?) {
//        with(binding) {
//            galleryAuthor.text = galleryItem.author
//            com.bumptech.glide.Glide
//                .with(root)
//                .load(galleryItem.url)
//                .centerCrop()
//                .placeholder(com.example.picsumgallery.R.drawable.ic_launcher_foreground)
//                .into(galleryImage)
//            galleryImage.setOnClickListener {
//                onClick?.onClick(galleryItem.id)
//            }
//        }
//    }
}
