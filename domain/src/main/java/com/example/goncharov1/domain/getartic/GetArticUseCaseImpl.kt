package com.example.goncharov1.domain.getartic

import com.example.goncharov1.domain.MainRepository
import com.example.goncharov1.domain.entity.ArticEntity
import javax.inject.Inject

class GetArticUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetArticUseCase {
    override suspend fun getArtic(id: Int): ArticEntity {
        return mainRepository.getArtic(id)
    }
}