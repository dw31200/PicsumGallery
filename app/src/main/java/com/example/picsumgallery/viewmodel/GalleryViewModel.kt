package com.example.picsumgallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.network.PicsumApi

class GalleryViewModel : ViewModel() {
    val picsumLiveData: LiveData<List<Picsum>>

    init {
        picsumLiveData = PicsumApi().fetchContents()
    }
}
