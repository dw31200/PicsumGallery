package com.example.picsumgallery.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.picsumgallery.data.local.model.PicsumLikeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PicsumLikeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(picsumLikeEntity: PicsumLikeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(picsumLikeEntityList: List<PicsumLikeEntity>)

    @Query("SELECT * FROM picsumLikeEntity ORDER BY ID ASC")
    fun getAll(): Flow<List<PicsumLikeEntity>>

    @Query("SELECT * FROM picsumLikeEntity WHERE id = :id")
    fun getItem(id: Int): Flow<PicsumLikeEntity?>

    @Query("SELECT * FROM picsumLikeEntity ORDER BY ID ASC LIMIT :limit OFFSET :offset")
    suspend fun getItemList(limit: Int, offset: Int): List<PicsumLikeEntity>

    @Update
    suspend fun update(picsumLikeEntity: PicsumLikeEntity)

    @Delete
    suspend fun delete(picsumLikeEntity: PicsumLikeEntity)
}
