package com.example.picsumgallery.data.local

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.picsumgallery.data.Picsum

class PicsumRepository(private val picsumDao: PicsumDao) {
    val allPicsums: LiveData<List<Picsum>> = picsumDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(vararg picsum: Picsum) {
        picsumDao.insertAll(*picsum)
    }
}
