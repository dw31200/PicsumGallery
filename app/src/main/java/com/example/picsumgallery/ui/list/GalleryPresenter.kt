package com.example.picsumgallery.ui.list

import com.example.picsumgallery.data.Picsum
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

        pageLoading { list ->
            view.setList(list)
        }
    }

    override fun onItemClicked(galleryId: Int) {
        view.navigateToDetail(galleryId)
    }

    override fun onLoadNextPage() {
        if (!nextLoading) return
        pageLoading { list ->
            view.addList(list)
        }
    }

    private fun pageLoading(listLoad: (List<Picsum>) -> Unit) {
        view.coroutineScope.launch {
            val list = model.fetchContents(page++)
            listLoad(list)
            view.hideProgress()

            nextLoading = list.size >= PicsumApiService.LIMIT
        }
    }
}
