package com.example.goncharov1.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private const val baseUrl = "https://api.artic.edu/"

        fun create(): ArticApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ArticApi::class.java)
        }
    }
}