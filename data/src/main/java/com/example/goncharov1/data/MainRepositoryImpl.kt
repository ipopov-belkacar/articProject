package com.example.goncharov1.data

import com.example.goncharov1.data.cache.ArticCache
import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.data.network.RetrofitClient
import com.example.goncharov1.domain.MainRepository
import com.example.goncharov1.domain.entity.ArticEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val articMapper: ArticMapper,
    private val articCache: ArticCache
) : MainRepository {

    override suspend fun getArtic(page: Int): List<ArticEntity> {

        val cashArticList = articCache.getArticList()

        return cashArticList.ifEmpty {
            val retrofit = RetrofitClient.create()
            val callGetArtic = retrofit.getArtic(page)
            val articEntityList = articMapper.toDomain(callGetArtic)
            articCache.setArticList(articEntityList)
            articEntityList
        }
    }
}