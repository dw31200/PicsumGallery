package com.example.picsumgallery

import android.app.Application
import timber.log.Timber

class PicsumGalleryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
