package com.example.goncharov1.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.goncharov1.domain.entity.ArticEntity

@Dao
interface ArticDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticListEntity(listArtic: List<ArticEntity>?)

    @Query("SELECT * FROM ArticEntity")
    fun getAllArtic(): PagingSource<Int, ArticEntity>

    @Query("DELETE FROM ArticEntity")
    suspend fun deleteAllArtic()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKey(listRemoteKet: List<ArticRemoteKey>)

    @Query("SELECT * FROM ArticRemoteKey WHERE id = :id")
    suspend fun getAllRemoteKey(id: Int): ArticRemoteKey?

    @Query("DELETE FROM ArticRemoteKey")
    suspend fun deleteAllArticRemoteKey()
}