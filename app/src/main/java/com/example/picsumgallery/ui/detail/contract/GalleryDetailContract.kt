package com.example.picsumgallery.ui.detail.contract

import com.example.picsumgallery.data.Picsum
import kotlinx.coroutines.CoroutineScope

interface GalleryDetailContract {
    interface View {
        val coroutineScope: CoroutineScope
        fun setItem(prevItem: Picsum?, currentItem: Picsum?, nextItem: Picsum?)
        fun showWebSite(galleryItem: Picsum)
    }

    interface Presenter {
        fun start(galleryId: Int)
        fun onUrlClicked(galleryItem: Picsum)
        fun onPrevButtonClicked(galleryId: Int)
        fun onNextButtonClicked(galleryId: Int)
    }
}