package com.example.goncharov1.data.cache

import com.example.goncharov1.domain.entity.ArticEntity

@Deprecated("Not currently in use")
interface ArticCache {

    fun getArticList(): List<ArticEntity>

    fun setArticList(articList: List<ArticEntity>)
}