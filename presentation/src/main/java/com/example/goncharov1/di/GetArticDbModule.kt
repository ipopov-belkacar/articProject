package com.example.goncharov1.di

import android.content.Context
import com.example.goncharov1.data.cache.disk.ArticDiskCache
import com.example.goncharov1.data.cache.disk.ArticDiskCacheImpl
import com.example.goncharov1.data.db.ArticDao
import com.example.goncharov1.data.db.ArticDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class GetArticDbModule {

    @Provides
    fun getArticDataBase(@ApplicationContext context: Context): ArticDatabase {
        return ArticDatabase.getInstance(context)
    }

    @Provides
    fun getArticDao(articDatabase: ArticDatabase): ArticDao {
        return articDatabase.getArticDao()
    }

    @Provides
    fun getArticDiskCache(articDao: ArticDao): ArticDiskCache {
        return ArticDiskCacheImpl(articDao)
    }
}