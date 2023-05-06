package com.example.picsumgallery.ui.list.contract

import com.example.picsumgallery.network.PicsumApi
import kotlinx.coroutines.launch

class GalleryPresenter(
    private val view: GalleryContract.View,
) : GalleryContract.Presenter {
    override fun start() {
        view.showProgress()

        view.coroutineScope.launch {
            val list = PicsumApi.retrofitService.fetchContents()
            view.setList(list)
            view.hideProgress()
        }
    }

    override fun onItemClicked(galleryId: Int) {
        view.navigateToDetail(galleryId)
    }
}
