package com.example.goncharov1.data.db

import androidx.paging.PagingSource
import androidx.room.*
import com.example.goncharov1.domain.entity.ArticEntity

@Dao
interface ArticDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticListEntity(listArtic: List<ArticEntity>?)

    @Query("SELECT * FROM ArticEntity WHERE id = :id")
    suspend fun getArticById(id: Int): ArticEntity?

    @Query("SELECT * FROM ArticEntity")
    fun getAllArtic(): PagingSource<Int, ArticEntity>

    @Query("DELETE FROM ArticEntity")
    suspend fun deleteAllArtic()
}