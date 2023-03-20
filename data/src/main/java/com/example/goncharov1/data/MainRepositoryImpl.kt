package com.example.goncharov1.data

import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.data.network.RetrofitClient
import com.example.goncharov1.domain.MainRepository
import com.example.goncharov1.domain.entity.ArticEntity

class MainRepositoryImpl(private val articMapper: ArticMapper) : MainRepository {

    override suspend fun getArtic(page: Int): List<ArticEntity> {

        val retrofit = RetrofitClient.create()
        val callGetArtic = retrofit.getArtic(page)

        return articMapper.toDomain(callGetArtic)
    }
}