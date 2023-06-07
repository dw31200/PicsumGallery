package com.example.picsumgallery.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.remote.PicsumApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class GalleryViewModel(
    private val model: GalleryModel,
) {
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
        model.fetchContents(page++)
            .onEach {
                _list.value = (_list.value ?: mutableListOf()) + it
                nextLoading = it.size >= PicsumApiService.LIMIT
            }.launchIn(CoroutineScope(Dispatchers.Main))
    }
}
