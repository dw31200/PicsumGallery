package com.example.picsumgallery.ui.list

import com.example.picsumgallery.data.Picsum
import kotlinx.coroutines.CoroutineScope

interface GalleryContract {
    interface Model {
        suspend fun fetchContents(page: Int): List<Picsum>
    }

    interface View {
        val coroutineScope: CoroutineScope

        fun setList(list: List<Picsum>)

        fun addList(list: List<Picsum>)

        fun navigateToDetail(galleryId: Int)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter {
        fun start()

        fun onItemClicked(galleryId: Int)

        fun onLoadNextPage()
    }
}
