package com.example.khalmatov_exam.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var BASE_URL = "https://623316726de3467dbac8199a.mockapi.io/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val apiService:ApiService = retrofit.create(ApiService::class.java)
}