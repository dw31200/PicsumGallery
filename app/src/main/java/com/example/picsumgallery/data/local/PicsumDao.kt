package com.example.picsumgallery.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.picsumgallery.data.local.model.PicsumEntity

// TODO Dao는 interface인데 PicsumDao를 상속받아 구현한 Impl은 쿼리문이 구현한다고 봐야하나요?
@Dao
interface PicsumDao {
    // TODO insert하는 값이 Null 일때 default 값을 미리 지정하는 방식보다는 null은 insert 안 되도록 처리하는 방식으로 하는게 맞지요?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(picsumEntity: PicsumEntity)

    /* TODO insert를 하고나서 받을 수 있는 결과 값이 있을까요? 예를 들어 새로 추가되는 값이 있는지 없는지를 알 방법이 있을까요?
        아니면 runCatching 과 같은 함수가 있을까요?
     */
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
