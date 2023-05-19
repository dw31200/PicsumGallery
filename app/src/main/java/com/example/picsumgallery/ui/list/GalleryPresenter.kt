package com.example.picsumgallery.ui.list

import com.example.picsumgallery.network.PicsumApiService
import kotlinx.coroutines.launch

class GalleryPresenter(
    private val view: GalleryContract.View,
    private val model: GalleryContract.Model,
) : GalleryContract.Presenter {
    private var page = 1
    private var nextLoading = true
    override fun start() {
        view.showProgress()

        view.coroutineScope.launch {
            val list = model.fetchContents(page)
            view.setList(list)
            view.hideProgress()
            page++

            if (list.size < PicsumApiService.LIMIT) {
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
            val list = model.fetchContents(page)
            view.addList(list)
            page++

            if (list.size < PicsumApiService.LIMIT) {
                nextLoading = false
            }
        }
    }
}
