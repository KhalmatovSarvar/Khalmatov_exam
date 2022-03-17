package com.example.khalmatov_exam.networking

import com.example.khalmatov_exam.model.Card
import retrofit2.Call
import retrofit2.http.GET


interface ApiService{
    @GET("cards")
    fun getCards(): Call<ArrayList<Card>>
}