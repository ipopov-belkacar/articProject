package com.example.goncharov1.data.cache.disk

import com.example.goncharov1.data.db.ArticDao
import com.example.goncharov1.domain.entity.ArticEntity
import javax.inject.Inject

class ArticDiskCacheImpl @Inject constructor(private val articDao: ArticDao) : ArticDiskCache {
    override suspend fun getArtic(id: Int): ArticEntity? {
        return articDao.getArticById(id)
    }
}