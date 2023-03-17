package com.example.goncharov1.domain.getArtic

import com.example.goncharov1.domain.entity.ArticEntity

interface GetArticUseCase {
    suspend fun getArtic(): List<ArticEntity>
}