package com.example.picsumgallery.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.picsumgallery.data.Picsum

@Dao
interface PicsumDao {
    @Query("SELECT * FROM picsum")
    fun getAll(): LiveData<List<Picsum>>

    @Query("SELECT * FROM picsum WHERE id LIKE :id")
    fun getItem(id: Int): Picsum

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg picsum: Picsum)

    @Query("DELETE FROM picsum")
    suspend fun deleteAll()
}
