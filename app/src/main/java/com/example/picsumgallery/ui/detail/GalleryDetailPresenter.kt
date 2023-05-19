package com.example.picsumgallery.ui.detail

import com.example.picsumgallery.data.Picsum
import kotlinx.coroutines.launch

class GalleryDetailPresenter(
    private val view: GalleryDetailContract.View,
    private val model: GalleryDetailContract.Model,
) : GalleryDetailContract.Presenter {
    override fun start(galleryId: Int) {
        view.coroutineScope.launch {
            val prevItem = model.getItem(galleryId - 1)
            val currentItem = model.getItem(galleryId)
            val nextItem = model.getItem(galleryId + 1)
            view.setItem(prevItem, currentItem, nextItem)
        }
    }

    override fun onUrlClicked(galleryItem: Picsum) {
        view.showWebSite(galleryItem)
    }

    override fun onPrevButtonClicked(galleryId: Int) {
        if (galleryId == 0) return
        view.coroutineScope.launch {
            val prevItem = model.getItem(galleryId - 2)
            val currentItem = model.getItem(galleryId - 1)
            val nextItem = model.getItem(galleryId)
            view.setItem(prevItem, currentItem, nextItem)
        }
    }

    override fun onNextButtonClicked(galleryId: Int) {
        if (galleryId == 1084) return
        view.coroutineScope.launch {
            val prevItem = model.getItem(galleryId)
            val currentItem = model.getItem(galleryId + 1)
            val nextItem = model.getItem(galleryId + 2)
            view.setItem(prevItem, currentItem, nextItem)
        }
    }
}
