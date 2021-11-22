package com.ddwan.news.Interface

import com.ddwan.news.model.NewsHeadlines
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CallAPI {

    @GET("v2/top-headlines")
    fun getNewsHeadline(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
    ): Call<NewsHeadlines>

    @GET("v2/everything")
    fun searchNews(
        @Query("q") q: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String,
    ): Call<NewsHeadlines>

}