package com.example.goncharov1.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.goncharov1.domain.entity.ArticEntity

@Database(entities = [ArticEntity::class, ArticRemoteKey::class], version = 1)
abstract class ArticDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): ArticDatabase {
            return Room.databaseBuilder(context, ArticDatabase::class.java, "ArticDatabase").build()
        }
    }

    abstract fun getArticDao(): ArticDao
}