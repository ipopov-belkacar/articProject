package com.example.goncharov1.domain.getartic

import com.example.goncharov1.domain.entity.ArticEntity

interface GetArticUseCase {
    suspend fun getArtic(id: Int): ArticEntity
}