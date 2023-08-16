package com.example.picsumgallery.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picsumgallery.domain.usecase.GetItemUseCase
import com.example.picsumgallery.domain.usecase.SetLikeItemUseCase
import com.example.picsumgallery.ui.model.PicsumUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class GalleryDetailViewModel @Inject constructor(
    private val getItemUseCase: GetItemUseCase,
    private val setLikeItemUseCase: SetLikeItemUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private var galleryId = savedStateHandle.getStateFlow("galleryId", -1)
    val prevItem: StateFlow<PicsumUiModel?> =
        savedStateHandle.getStateFlow<Int>("galleryId", -1)
            .flatMapLatest { galleryId ->
                getItemUseCase(galleryId - 1)
            }.map {
                it?.let { PicsumUiModel(it) }
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                null,
            )
    val currentItem: StateFlow<PicsumUiModel?> =
        savedStateHandle.getStateFlow<Int>("galleryId", -1)
            .flatMapLatest { galleryId ->
                getItemUseCase(galleryId)
            }.map {
                it?.let { PicsumUiModel(it) }
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                null,
            )
    val nextItem: StateFlow<PicsumUiModel?> =
        savedStateHandle.getStateFlow<Int>("galleryId", -1)
            .flatMapLatest { galleryId ->
                getItemUseCase(galleryId + 1)
            }.map {
                it?.let { PicsumUiModel(it) }
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                null,
            )

    fun onPrevButtonClicked() {
        savedStateHandle["galleryId"] = galleryId.value - 1
    }

    fun onNextButtonClicked() {
        savedStateHandle["galleryId"] = galleryId.value + 1
    }

    fun onLikeButtonClicked() {
        viewModelScope.launch {
            currentItem.value?.let { setLikeItemUseCase(it.toPicsumEntity()) }
        }
    }
}
