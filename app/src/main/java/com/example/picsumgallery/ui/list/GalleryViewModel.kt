package com.example.picsumgallery.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.network.PicsumApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GalleryViewModel(
    private val model: GalleryModel,
) {
    private var page: Int = 1
    private var nextLoading: Boolean = true
    private val _isShowProgress = MutableLiveData(true)
    val isShowProgress: LiveData<Boolean>
        get() = _isShowProgress
    private val _list = MutableLiveData<List<Picsum>>()
    val list: LiveData<List<Picsum>>
        get() = _list

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val list = model.fetchContents(page++)
            withContext(Dispatchers.Main) {
                _list.value = list
                _isShowProgress.value = false
            }

            if (list.size < PicsumApiService.LIMIT) {
                nextLoading = false
            }
        }
    }

    fun onLoadNextPage() {
        if (!nextLoading) return
        CoroutineScope(Dispatchers.IO).launch {
            val list = model.fetchContents(page++)
            withContext(Dispatchers.Main) {
                _list.value = (_list.value ?: mutableListOf()) + list
            }

            if (list.size < PicsumApiService.LIMIT) {
                nextLoading = false
            }
        }
    }
}
