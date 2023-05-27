package com.example.picsumgallery.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.picsumgallery.data.Picsum
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GalleryDetailViewModel(
    private val model: GalleryDetailModel,
) {
    var galleryId: Int = 0
    private val _prevItem = MutableLiveData<Picsum>()
    val prevItem: LiveData<Picsum>
        get() = _prevItem
    private val _currentItem = MutableLiveData<Picsum>()
    val currentItem: LiveData<Picsum>
        get() = _currentItem
    private val _nextItem = MutableLiveData<Picsum>()
    val nextItem: LiveData<Picsum>
        get() = _nextItem
    private val _isShowPrevImage = MutableLiveData(true)
    val isShowPrevImage: LiveData<Boolean>
        get() = _isShowPrevImage
    private val _isShowNextImage = MutableLiveData(true)
    val isShowNextImage: LiveData<Boolean>
        get() = _isShowNextImage
    private val _isEnabledPrevButton = MutableLiveData(true)
    val isEnabledPrevButton: LiveData<Boolean>
        get() = _isEnabledPrevButton
    private val _isEnabledNextButton = MutableLiveData(true)
    val isEnabledNextButton: LiveData<Boolean>
        get() = _isEnabledNextButton

    //    todo viewModel로 분리시켜야 하는 경우와 fragment에서 직접 연결하는 차이
//    todo fragment를 viewModel로 사용하니 MVC가 되는 느낌...
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
            if (_prevItem.value == null) {
                _isShowPrevImage.value = false
                _isEnabledPrevButton.value = false
            } else {
                _isShowPrevImage.value = true
                _isEnabledPrevButton.value = true
            }
            _currentItem.value = model.getItem(galleryId)
            _nextItem.value = model.getItem(galleryId + 1)
            if (_nextItem.value == null) {
                _isShowNextImage.value = false
                _isEnabledNextButton.value = false
            } else {
                _isShowNextImage.value = true
                _isEnabledNextButton.value = true
            }
        }
    }
}
