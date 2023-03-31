package com.example.goncharov1.data.cache.inmemory

import com.example.goncharov1.domain.entity.ArticEntity
import javax.inject.Inject
import javax.inject.Singleton

@Deprecated("Not currently in use")
@Singleton
class ArticInMemoryCacheImpl @Inject constructor() : ArticInMemoryCache {

    private val cashListArtic: MutableList<ArticEntity> = mutableListOf()

    override fun getArticList(): List<ArticEntity> {
        return cashListArtic
    }

    override fun setArticList(articList: List<ArticEntity>) {
        cashListArtic.clear()
        cashListArtic.addAll(articList)
    }
}