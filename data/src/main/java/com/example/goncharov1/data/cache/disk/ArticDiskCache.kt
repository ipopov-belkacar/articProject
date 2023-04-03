package com.example.goncharov1.data.cache.disk

import com.example.goncharov1.domain.entity.ArticEntity

interface ArticDiskCache {
    suspend fun getArtic(id: Int): ArticEntity?
}