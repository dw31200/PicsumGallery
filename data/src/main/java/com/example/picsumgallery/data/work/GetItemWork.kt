package com.example.picsumgallery.data.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.picsumgallery.data.local.PicsumDao
import com.example.picsumgallery.data.model.Picsum
import com.example.picsumgallery.data.remote.PicsumApiService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class GetItemWork @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val picsumDao: PicsumDao,
    private val picsumApiService: PicsumApiService,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val galleryId = inputData.getInt("galleryId", -1)
        val remote = picsumApiService.getItem(galleryId)?.let { Picsum(it) }
        remote?.let {
            picsumDao.insert(it.toEntity())
            return Result.success()
        } ?: run {
            return Result.failure()
        }
    }
}
