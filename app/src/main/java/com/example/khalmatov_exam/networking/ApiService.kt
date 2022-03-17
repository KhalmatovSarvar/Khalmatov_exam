package com.example.khalmatov_exam.networking

import com.example.khalmatov_exam.model.Card
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService{
    @GET("cards")
    fun getCards(): Call<ArrayList<Card>>

    @POST("cards")
    fun createCard(@Body card: Card):Call<Card>
}