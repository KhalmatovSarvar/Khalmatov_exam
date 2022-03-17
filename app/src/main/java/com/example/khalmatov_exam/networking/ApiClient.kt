package com.example.khalmatov_exam.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


    object ApiClient {
        private var BASE_URL = "https://623316726de3467dbac8199a.mockapi.io/"
        private fun getRETROFIT(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }

        fun <T> createService(service: Class<T>): T {
            return getRETROFIT().create(service)
        }
    }