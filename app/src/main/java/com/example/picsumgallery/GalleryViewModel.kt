package com.example.picsumgallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {
    val picsumLiveData: LiveData<List<Picsum>>

    init {
        picsumLiveData = PicsumApi.fetchContents()
    }
}