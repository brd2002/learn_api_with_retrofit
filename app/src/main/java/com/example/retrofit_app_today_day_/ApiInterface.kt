package com.example.retrofit_app_today_day_

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("comments")
    fun getComment(): Call<List<CommentsItem>>
}