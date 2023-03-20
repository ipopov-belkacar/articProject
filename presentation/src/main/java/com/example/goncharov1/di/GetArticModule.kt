package com.example.goncharov1.di

import com.example.goncharov1.data.MainRepositoryImpl
import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.data.mappers.ArticMapperImpl
import com.example.goncharov1.domain.MainRepository
import com.example.goncharov1.domain.getArtic.GetArticUseCase
import com.example.goncharov1.domain.getArtic.GetArticUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class GetArticModule {

    @Provides
    fun getArticMapper(): ArticMapper {
        return ArticMapperImpl()
    }

    @Provides
    fun getMainRepository(articMapper: ArticMapper): MainRepository {
        return MainRepositoryImpl(articMapper)
    }

    @Provides
    fun getArticUseCase(mainRepository: MainRepository): GetArticUseCase {
        return GetArticUseCaseImpl(mainRepository)
    }
}