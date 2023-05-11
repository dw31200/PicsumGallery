package com.example.picsumgallery.ui.detail.contract

import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.network.PicsumApi
import kotlinx.coroutines.launch

class GalleryDetailPresenter(
    private val view: GalleryDetailContract.View,
) : GalleryDetailContract.Presenter {
    override fun start(galleryId: Int) {
        view.coroutineScope.launch {
            if (galleryId == 0) {
                val currentItem = PicsumApi.retrofitService.getItem(galleryId)
                val nextItem = PicsumApi.retrofitService.getItem(galleryId + 1)
                view.setItem(null, currentItem, nextItem)
            } else {
                val prevItem = PicsumApi.retrofitService.getItem(galleryId - 1)
                val currentItem = PicsumApi.retrofitService.getItem(galleryId)
                val nextItem = PicsumApi.retrofitService.getItem(galleryId + 1)
                view.setItem(prevItem, currentItem, nextItem)
            }
        }
    }

    override fun onUrlClicked(galleryItem: Picsum) {
        view.showWebSite(galleryItem)
    }

    override fun onPrevButtonClicked(galleryId: Int) {
        if (galleryId == 0) {
            return
        }
        view.coroutineScope.launch {
            if (galleryId - 1 == 0) {
                val currentItem = PicsumApi.retrofitService.getItem(galleryId - 1)
                val nextItem = PicsumApi.retrofitService.getItem(galleryId)
                view.setItem(null, currentItem, nextItem)
            } else {
                val prevItem = PicsumApi.retrofitService.getItem(galleryId - 2)
                val currentItem = PicsumApi.retrofitService.getItem(galleryId - 1)
                val nextItem = PicsumApi.retrofitService.getItem(galleryId)
                view.setItem(prevItem, currentItem, nextItem)
            }
        }
    }

    override fun onNextButtonClicked(galleryId: Int) {
        view.coroutineScope.launch {
            val prevItem = PicsumApi.retrofitService.getItem(galleryId)
            val currentItem = PicsumApi.retrofitService.getItem(galleryId + 1)
            val nextItem = PicsumApi.retrofitService.getItem(galleryId + 2)
            view.setItem(prevItem, currentItem, nextItem)
        }
    }
}
