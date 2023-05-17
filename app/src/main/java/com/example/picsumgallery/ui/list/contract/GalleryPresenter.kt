package com.example.picsumgallery.ui.list.contract

import com.example.picsumgallery.network.PicsumApi
import kotlinx.coroutines.launch

class GalleryPresenter(
    private val view: GalleryContract.View,
) : GalleryContract.Presenter {
    private var page = 1
    private var nextLoading = true
    override fun start() {
        view.showProgress()

        view.coroutineScope.launch {
            val list = PicsumApi.retrofitService.fetchContents(page)
            view.setList(list)
            view.hideProgress()
            page++

            if (list.size < 30) {
                nextLoading = false
            }
        }
    }

    override fun onItemClicked(galleryId: Int) {
        view.navigateToDetail(galleryId)
    }

    override fun onLoadNextPage() {
        if (!nextLoading) return
        view.coroutineScope.launch {
            val list = PicsumApi.retrofitService.fetchContents(page)
            view.addList(list)
            page++

            if (list.size < 30) {
                nextLoading = false
            }
        }
    }
}
