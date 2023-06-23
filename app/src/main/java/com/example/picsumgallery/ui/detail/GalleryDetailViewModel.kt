package com.example.picsumgallery.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.picsumgallery.data.model.Picsum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GalleryDetailViewModel @Inject constructor(
    private val model: GalleryDetailModel,
    // TODO 같은 타입이지만 다른 값을 inject 하려면 어떻게 해야할까요?
    private var galleryId: Int,
    // TODO Inject 생성자중에 Hilt로 주입 받지 않고 따로 생성자를 파라미터로 받는 방법이 있을까요? 아니면 모든 생성자를 모듈에 만들어놔야 하는 건가요?
) : ViewModel() {
    private val _prevItem = MutableLiveData<Picsum?>()
    val prevItem: LiveData<Picsum?>
        get() = _prevItem
    private val _currentItem = MutableLiveData<Picsum?>()
    val currentItem: LiveData<Picsum?>
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
        model
            .getItem(galleryId - 1)
            .onEach {
                _prevItem.value = it
            }
            .launchIn(viewModelScope)
        model
            .getItem(galleryId)
            .onEach {
                _currentItem.value = it
            }
            .launchIn(viewModelScope)
        model
            .getItem(galleryId + 1)
            .onEach {
                _nextItem.value = it
            }
            .launchIn(viewModelScope)
    }
}
