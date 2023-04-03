package com.example.goncharov1.di

import com.example.goncharov1.data.MainRepositoryImpl
import com.example.goncharov1.data.cache.inmemory.ArticInMemoryCache
import com.example.goncharov1.data.cache.inmemory.ArticInMemoryCacheImpl
import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.data.mappers.ArticMapperImpl
import com.example.goncharov1.domain.MainRepository
import com.example.goncharov1.domain.getartic.GetArticUseCase
import com.example.goncharov1.domain.getartic.GetArticUseCaseImpl
import com.example.goncharov1.domain.getarticlist.GetArticListUseCase
import com.example.goncharov1.domain.getarticlist.GetArticListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class GetArticModule {

    @Binds
    abstract fun getArticCache(articCacheImpl: ArticInMemoryCacheImpl): ArticInMemoryCache

    @Binds
    abstract fun getArticMapper(articMapperImpl: ArticMapperImpl): ArticMapper

    @Binds
    abstract fun getMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun getArticUseCase(getArticUseCaseImpl: GetArticUseCaseImpl): GetArticUseCase

    @Binds
    abstract fun getArticListUseCase(getArticListUseCaseImpl: GetArticListUseCaseImpl): GetArticListUseCase

}