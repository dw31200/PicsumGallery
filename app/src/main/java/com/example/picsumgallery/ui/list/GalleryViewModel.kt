package com.example.picsumgallery.ui.list

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
class GalleryViewModel @Inject constructor(
    private val model: GalleryModel,
    private val limit: Int,
) : ViewModel() {
    // TODO 계속 변해야하는 값도 Hilt로 Di 가능한가요?
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
        model
            .fetchContents(page++)
            .onEach {
                _list.value = (_list.value ?: mutableListOf()) + it
                nextLoading = it.size >= limit
            }
            .launchIn(viewModelScope)
    }
}
