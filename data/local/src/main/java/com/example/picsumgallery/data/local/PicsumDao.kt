package com.example.picsumgallery.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.picsumgallery.data.local.model.PicsumEntity

@Dao
interface PicsumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(picsumEntity: PicsumEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(picsumEntityList: List<PicsumEntity>)

    @Query("SELECT * FROM picsumEntity ORDER BY ID ASC")
    suspend fun getAll(): List<PicsumEntity>

    @Query("SELECT * FROM picsumEntity WHERE id = :id")
    suspend fun getItem(id: Int): PicsumEntity?

    @Query("SELECT * FROM picsumEntity ORDER BY ID ASC LIMIT :limit OFFSET :offset")
    suspend fun getItemList(limit: Int, offset: Int): List<PicsumEntity>

    @Update
    suspend fun update(picsumEntity: PicsumEntity)

    @Delete
    suspend fun deleteAll(picsumEntity: PicsumEntity)
}
