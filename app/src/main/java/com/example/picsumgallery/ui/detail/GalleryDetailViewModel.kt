package com.example.picsumgallery.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.picsumgallery.data.Picsum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryDetailViewModel(
    private val model: GalleryDetailModel,
) {
    var galleryId: Int = 0
    private val _prevItem = MutableLiveData<Picsum?>()
    val prevItem: LiveData<Picsum?>
        get() = _prevItem
    private val _currentItem = MutableLiveData<Picsum>()
    val currentItem: LiveData<Picsum>
        get() = _currentItem
    private val _nextItem = MutableLiveData<Picsum?>()
    val nextItem: LiveData<Picsum?>
        get() = _nextItem
    val isShowPrevImage: LiveData<Boolean> = _prevItem.map { it != null }
    val isShowNextImage: LiveData<Boolean> = _nextItem.map { it != null }
    val isEnabledPrevButton: LiveData<Boolean> = _prevItem.map { it != null }
    val isEnabledNextButton: LiveData<Boolean> = _nextItem.map { it != null }

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
        CoroutineScope(Dispatchers.Main).launch {
            _prevItem.value = model.getItem(galleryId - 1)
            _currentItem.value = model.getItem(galleryId)
            _nextItem.value = model.getItem(galleryId + 1)
        }
    }
}
