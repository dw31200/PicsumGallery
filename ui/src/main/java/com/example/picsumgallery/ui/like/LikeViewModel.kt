package com.example.picsumgallery.ui.like

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picsumgallery.domain.usecase.GetLikeItemListUseCase
import com.example.picsumgallery.ui.model.PicsumUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val getLikeItemListUseCase: GetLikeItemListUseCase,
) : ViewModel() {
    private val _likeItemList = MutableLiveData<List<PicsumUiModel>>()
    val likeItemList: LiveData<List<PicsumUiModel>>
        get() = _likeItemList

    init {
        fetchLikeItem()
    }

    private fun fetchLikeItem() {
        getLikeItemListUseCase()
            .map { it.map { PicsumUiModel(it) } }
            .onEach {
                _likeItemList.value = (_likeItemList.value ?: mutableListOf()) + it
            }
            .launchIn(viewModelScope)
    }
}
