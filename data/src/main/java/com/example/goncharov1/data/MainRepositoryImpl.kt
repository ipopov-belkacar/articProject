package com.example.goncharov1.data

import com.example.goncharov1.data.cache.disk.ArticDiskCache
import com.example.goncharov1.data.cache.inmemory.ArticInMemoryCache
import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.data.network.RetrofitClient
import com.example.goncharov1.domain.MainRepository
import com.example.goncharov1.domain.entity.ArticEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val articMapper: ArticMapper,
    private val articInMemoryCache: ArticInMemoryCache,
    private val articDiskCache: ArticDiskCache
) : MainRepository {

    override suspend fun getArtic(id: Int): ArticEntity {
        return articDiskCache.getArtic(id)
            ?: throw java.lang.IllegalArgumentException("Not found entity")
    }

    @Deprecated("Not currently in use")
    override suspend fun getArticList(page: Int): List<ArticEntity> {

        val cashArticList = articInMemoryCache.getArticList()

        return cashArticList.ifEmpty {
            val retrofit = RetrofitClient.create()
            val callGetArtic = retrofit.getArtic(page)
            val articEntityList = articMapper.toDomain(callGetArtic.body()!!)
//            articInMemoryCache.setArticList(articEntityList)
            articEntityList
        }
    }
}