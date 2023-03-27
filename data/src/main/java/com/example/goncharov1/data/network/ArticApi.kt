package com.example.goncharov1.data.network

import com.example.goncharov1.data.model.ArticModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticApi {
    @GET("api/v1/artworks")
    suspend fun getArtic(@Query("page") page: Int): Response<ArticModel>
}