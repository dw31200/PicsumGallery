package com.example.picsumgallery.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.picsumgallery.domain.usecase.GetItemListUseCase
import com.example.picsumgallery.ui.model.PicsumUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getItemListUseCase: GetItemListUseCase,
) : ViewModel() {
    val list = getItemListUseCase()
        .cachedIn(viewModelScope)
        .map {
            it.map {
                PicsumUiModel(it)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            PagingData.empty(),
        )
}
