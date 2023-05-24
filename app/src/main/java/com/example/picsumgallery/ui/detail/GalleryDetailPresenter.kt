package com.example.picsumgallery.ui.detail

import com.example.picsumgallery.data.Picsum
import kotlinx.coroutines.launch

class GalleryDetailPresenter(
    private val view: GalleryDetailContract.View,
    private val model: GalleryDetailContract.Model,
    private var galleryId: Int = 0,
) : GalleryDetailContract.Presenter {
    override fun start() {
        detailLoad(
            currentItem = { if (it != null) view.showCurrentItem(it) },
            prevItem = { if (it != null) view.showPrevItem(it) else view.hidePrevItem() },
            nextItem = { if (it != null) view.showNextItem(it) else view.hideNextItem() },
        )
    }

    override fun onUrlClicked(galleryItem: Picsum) {
        view.showWebSite(galleryItem)
    }

    override fun onPrevButtonClicked() {
        galleryId--
        detailLoad(
            currentItem = { if (it != null) view.showCurrentItem(it) },
            prevItem = { if (it != null) view.showPrevItem(it) else view.hidePrevItem() },
            nextItem = { if (it != null) view.showNextItem(it) else view.hideNextItem() },
        )
    }

    override fun onNextButtonClicked() {
        galleryId++
        detailLoad(
            currentItem = { if (it != null) view.showCurrentItem(it) },
            prevItem = { if (it != null) view.showPrevItem(it) else view.hidePrevItem() },
            nextItem = { if (it != null) view.showNextItem(it) else view.hideNextItem() },
        )
    }

    private fun detailLoad(
        currentItem: (Picsum?) -> Unit,
        prevItem: (Picsum?) -> Unit,
        nextItem: (Picsum?) -> Unit,
    ) {
        view.coroutineScope.launch {
            prevItem(model.getItem(galleryId - 1))
            currentItem(model.getItem(galleryId))
            nextItem(model.getItem(galleryId + 1))
        }
    }
}
