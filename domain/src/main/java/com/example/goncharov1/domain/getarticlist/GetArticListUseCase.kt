package com.example.goncharov1.domain.getarticlist

import com.example.goncharov1.domain.entity.ArticEntity

interface GetArticListUseCase {
    suspend fun getArticList(page: Int): List<ArticEntity>
}