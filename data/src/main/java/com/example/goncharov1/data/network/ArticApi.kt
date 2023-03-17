package com.example.goncharov1.data.network

import com.example.goncharov1.data.model.ArticModel
import retrofit2.http.GET

interface ArticApi {
    @GET("api/v1/artworks")
    suspend fun getArtic(): ArticModel
}