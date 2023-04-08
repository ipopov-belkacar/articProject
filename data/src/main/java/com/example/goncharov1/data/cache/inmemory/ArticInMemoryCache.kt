package com.example.goncharov1.data.cache.inmemory

import com.example.goncharov1.domain.entity.ArticEntity

@Deprecated("Not currently in use")
interface ArticInMemoryCache {

    fun getArticList(): List<ArticEntity>

    fun setArticList(articList: List<ArticEntity>)
}