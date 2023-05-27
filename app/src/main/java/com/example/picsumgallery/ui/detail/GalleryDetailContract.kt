package com.example.picsumgallery.ui.detail

import com.example.picsumgallery.data.Picsum
import kotlinx.coroutines.CoroutineScope

interface GalleryDetailContract {
    interface Model {
        suspend fun getItem(galleryId: Int): Picsum?
    }

    interface View {
        val coroutineScope: CoroutineScope
        fun showCurrentItem(currentItem: Picsum)
        fun showWebSite(galleryItem: Picsum)
        fun showPrevItem(prevItem: Picsum)
        fun showNextItem(nextItem: Picsum)
        fun hidePrevItem()
        fun hideNextItem()
    }

    interface Presenter {
        fun start()
        fun onUrlClicked(galleryItem: Picsum)
        fun onPrevButtonClicked()
        fun onNextButtonClicked()
    }
}
