package com.example.picsumgallery.ui.like

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picsumgallery.domain.usecase.GetLikeItemListUseCase
import com.example.picsumgallery.ui.model.PicsumUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val getLikeItemListUseCase: GetLikeItemListUseCase,
) : ViewModel() {
    val likeItemList: StateFlow<List<PicsumUiModel>> = getLikeItemListUseCase().map {
        it.map {
            PicsumUiModel(it)
        }
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            mutableListOf(),
        )

//    init {
//        fetchLikeItem()
//    }
//
//    private fun fetchLikeItem() {
//        getLikeItemListUseCase()
//            .map { it.map { PicsumUiModel(it) } }
//            .onEach {
//                _likeItemList.value = (_likeItemList.value ?: mutableListOf()) + it
//            }
//            .launchIn(viewModelScope)
//    }
}
