package com.example.goncharov1.data.cache

import com.example.goncharov1.domain.entity.ArticEntity
import javax.inject.Inject
import javax.inject.Singleton

@Deprecated("Not currently in use")
@Singleton
class ArticCacheImpl @Inject constructor() : ArticCache {

    private val cashListArtic: MutableList<ArticEntity> = mutableListOf()

    override fun getArticList(): List<ArticEntity> {
        return cashListArtic
    }

    override fun setArticList(articList: List<ArticEntity>) {
        cashListArtic.clear()
        cashListArtic.addAll(articList)
    }
}