package com.example.picsumgallery.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.picsumgallery.data.remote.Limit
import com.example.picsumgallery.domain.usecase.FetchContentsUsecase
import com.example.picsumgallery.ui.model.Picsum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val useCase: FetchContentsUsecase,
    @com.example.picsumgallery.data.remote.Limit private val limit: Int,
) : ViewModel() {
    private var page: Int = 1
    private var nextLoading: Boolean = true
    private val _list = MutableLiveData<List<Picsum>>()
    val list: LiveData<List<Picsum>>
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
            .onEach {
                _list.value = (_list.value ?: mutableListOf()) + it
                nextLoading = it.size >= limit
            }
            .launchIn(viewModelScope)
    }
}
