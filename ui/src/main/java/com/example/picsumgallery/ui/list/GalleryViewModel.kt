package com.example.picsumgallery.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.picsumgallery.domain.usecase.GetItemListUseCase
import com.example.picsumgallery.share.Contract
import com.example.picsumgallery.ui.model.PicsumUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val useCase: GetItemListUseCase,
) : ViewModel() {
    private val limit = Contract.LIMIT
    private var page: Int = 1
    private var nextLoading: Boolean = true
    private val _list = MutableLiveData<List<PicsumUiModel>>()
    val list: LiveData<List<PicsumUiModel>>
        get() = _list
    val isShowProgress: LiveData<Boolean> = _list.map { it.isEmpty() }

    init {
        fetchContents()
    }

    fun onLoadNextPage() {
        if (!nextLoading) return
        fetchContents()
    }

    private fun fetchContents() {
        useCase(page++, limit)
            .map { it.map { PicsumUiModel(it) } }
            .onEach {
                _list.value = (_list.value ?: mutableListOf()) + it
                nextLoading = it.size >= limit
            }
            .launchIn(viewModelScope)
    }
}
