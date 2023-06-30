package com.example.picsumgallery.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.picsumgallery.domain.usecase.GetItemUseCase
import com.example.picsumgallery.ui.detail.GalleryDetailFragment.Companion.BUNDLE_ID
import com.example.picsumgallery.ui.model.Picsum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GalleryDetailViewModel @Inject constructor(
    private val useCase: GetItemUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private var galleryId: Int = savedStateHandle.get<Int>(BUNDLE_ID) ?: -1
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
        useCase(galleryId - 1)
            .onEach {
                _prevItem.value = it
            }
            .launchIn(viewModelScope)
        useCase(galleryId)
            .onEach {
                _currentItem.value = it
            }
            .launchIn(viewModelScope)
        useCase(galleryId + 1)
            .onEach {
                _nextItem.value = it
            }
            .launchIn(viewModelScope)
    }
}
