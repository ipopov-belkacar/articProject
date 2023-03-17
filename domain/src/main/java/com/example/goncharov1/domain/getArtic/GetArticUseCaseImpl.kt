package com.example.goncharov1.domain.getArtic

import com.example.goncharov1.domain.MainRepository
import com.example.goncharov1.domain.entity.ArticEntity

class GetArticUseCaseImpl(val mainRepository: MainRepository) : GetArticUseCase {
    override suspend fun getArtic(): List<ArticEntity> {
        return mainRepository.getArtic()
    }
}