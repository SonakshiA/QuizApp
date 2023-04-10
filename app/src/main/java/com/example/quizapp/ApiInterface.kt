package com.example.quizapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("questions?categories=science&limit=10&region=IN&difficulty=easy") //the end-point
    fun getData():Call<ResponseDataClass>
}