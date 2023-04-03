package com.example.goncharov1.domain.getarticlist

import com.example.goncharov1.domain.MainRepository
import com.example.goncharov1.domain.entity.ArticEntity
import javax.inject.Inject

class GetArticListUseCaseImpl @Inject constructor(private val mainRepository: MainRepository) :
    GetArticListUseCase {
    override suspend fun getArticList(page: Int): List<ArticEntity> {
        return mainRepository.getArticList(page)
    }
}