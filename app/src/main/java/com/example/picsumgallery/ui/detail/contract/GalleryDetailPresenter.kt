package com.example.picsumgallery.ui.detail.contract

import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.network.PicsumApi
import kotlinx.coroutines.launch

class GalleryDetailPresenter(
    private val view: GalleryDetailContract.View,
) : GalleryDetailContract.Presenter {
    override fun start(galleryId: Int) {
        view.coroutineScope.launch {
            val item = PicsumApi.retrofitService.getItem(galleryId)
            view.setItem(item)
        }
    }

    override fun onUrlClicked(galleryItem: Picsum) {
        view.showWebSite(galleryItem)
    }
}
