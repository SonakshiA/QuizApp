package com.example.quizapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://the-trivia-api.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

     val apiInterface by lazy{
        retrofit.create(ApiInterface::class.java)
    }
}