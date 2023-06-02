package com.example.picsumgallery

import android.app.Application
import com.example.picsumgallery.data.local.PicsumDatabase
import com.example.picsumgallery.data.local.PicsumRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

class PicsumGalleryApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { PicsumDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { PicsumRepository(database.picsumDao()) }
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
