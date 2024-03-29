package com.example.goncharov1.domain

import com.example.goncharov1.domain.entity.ArticEntity

interface MainRepository {

    suspend fun getArtic(id: Int): ArticEntity

    suspend fun getArticList(page: Int): List<ArticEntity>
}