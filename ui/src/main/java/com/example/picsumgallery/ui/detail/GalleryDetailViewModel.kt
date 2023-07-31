package com.example.picsumgallery.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.picsumgallery.domain.usecase.GetItemUseCase
import com.example.picsumgallery.ui.detail.GalleryDetailFragment.Companion.BUNDLE_ID
import com.example.picsumgallery.ui.model.PicsumUiModel
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
    private val _prevItem = MutableLiveData<PicsumUiModel?>()
    val prevItem: LiveData<PicsumUiModel?>
        get() = _prevItem
    private val _currentItem = MutableLiveData<PicsumUiModel?>()
    val currentItem: LiveData<PicsumUiModel?>
        get() = _currentItem
    private val _nextItem = MutableLiveData<PicsumUiModel?>()
    val nextItem: LiveData<PicsumUiModel?>
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
                it?.let { _prevItem.value = PicsumUiModel(it) } ?: run { _prevItem.value = null }
            }
            .launchIn(viewModelScope)
        useCase(galleryId)
            .onEach {
                it?.let { _currentItem.value = PicsumUiModel(it) }
            }
            .launchIn(viewModelScope)
        useCase(galleryId + 1)
            .onEach {
                it?.let { _nextItem.value = PicsumUiModel(it) } ?: run { _nextItem.value = null }
            }
            .launchIn(viewModelScope)
    }
}
