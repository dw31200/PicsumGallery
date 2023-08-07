package com.example.picsumgallery.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.picsumgallery.data.local.model.PicsumLikeEntity

@Dao
interface PicsumLikeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(picsumLikeEntity: PicsumLikeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(picsumLikeEntityList: List<PicsumLikeEntity>)

    @Query("SELECT * FROM picsumLikeEntity ORDER BY ID ASC")
    suspend fun getAll(): List<PicsumLikeEntity>

    @Query("SELECT * FROM picsumLikeEntity WHERE id = :id")
    suspend fun getItem(id: Int): PicsumLikeEntity?

    @Query("SELECT * FROM picsumLikeEntity ORDER BY ID ASC LIMIT :limit OFFSET :offset")
    suspend fun getItemList(limit: Int, offset: Int): List<PicsumLikeEntity>

    @Update
    suspend fun update(picsumLikeEntity: PicsumLikeEntity)

    @Delete
    suspend fun deleteAll(picsumLikeEntity: PicsumLikeEntity)
}
