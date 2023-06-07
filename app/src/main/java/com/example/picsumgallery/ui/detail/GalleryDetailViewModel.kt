package com.example.picsumgallery.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.picsumgallery.data.model.Picsum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class GalleryDetailViewModel(
    private val model: GalleryDetailModel,
    private var galleryId: Int,
) {
    private val _prevItem = MutableLiveData<Picsum>()
    val prevItem: LiveData<Picsum>
        get() = _prevItem
    private val _currentItem = MutableLiveData<Picsum>()
    val currentItem: LiveData<Picsum>
        get() = _currentItem
    private val _nextItem = MutableLiveData<Picsum>()
    val nextItem: LiveData<Picsum>
        get() = _nextItem
    val isShowPrevImage: LiveData<Boolean> = _prevItem.map { it.id != -1 }
    val isShowNextImage: LiveData<Boolean> = _nextItem.map { it.id != -1 }
    val isEnabledPrevButton: LiveData<Boolean> = _prevItem.map { it.id != -1 }
    val isEnabledNextButton: LiveData<Boolean> = _nextItem.map { it.id != -1 }

    init {
        loadDetailView()
    }

    fun onPrevButtonClicked() {
        galleryId--
        loadDetailView()
    }

    fun onNextButtonClicked() {
        galleryId++
        loadDetailView()
    }

    private fun loadDetailView() {
        model.getItem(galleryId - 1)
            .onEach {
                _prevItem.value = it
            }.launchIn(CoroutineScope(Dispatchers.Main))
        model.getItem(galleryId)
            .onEach {
                _currentItem.value = it
            }.launchIn(CoroutineScope(Dispatchers.Main))
        model.getItem(galleryId + 1)
            .onEach {
                _nextItem.value = it
            }.launchIn(CoroutineScope(Dispatchers.Main))
    }
}
