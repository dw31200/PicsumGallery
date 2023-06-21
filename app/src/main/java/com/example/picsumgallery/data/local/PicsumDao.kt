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
    //  todo Null 일때 default 값을 미리 지정하는 방식보다는 null은 insert 안 되도록 처리하는 방식으로 하는게 맞지요?
    /* todo 기본 동작인 ABORT 는 SQL 문을 중단하고 백업하지만,
        동일한 트랜젝션 내의 이전 SQL 문으로 인해 발생한 변경 사항은 보존되고 트랜젝션은 활성상태로 유지된다.
        트랜젝션의 의미?
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(picsumEntity: PicsumEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(picsumEntityList: List<PicsumEntity>)

    @Query("SELECT * FROM picsumEntity ORDER BY ID ASC")
    suspend fun getAll(): List<PicsumEntity>

    @Query("SELECT * FROM picsumEntity WHERE id = :id")
    suspend fun getItem(id: Int): PicsumEntity?

    @Query("SELECT * FROM picsumEntity ORDER BY ID ASC LIMIT :limit OFFSET :offset")
    suspend fun getItem(limit: Int, offset: Int): List<PicsumEntity>

    @Update
    suspend fun update(picsumEntity: PicsumEntity)

    @Delete
    suspend fun deleteAll(picsumEntity: PicsumEntity)
}
