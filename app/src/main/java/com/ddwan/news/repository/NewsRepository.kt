package com.ddwan.news.repository

import androidx.lifecycle.MutableLiveData
import com.ddwan.news.Interface.CallAPI
import com.ddwan.news.config.Constants.Companion.API_KEY
import com.ddwan.news.config.Constants.Companion.BASE_URL
import com.ddwan.news.model.Article
import com.ddwan.news.model.NewsHeadlines
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository {
    companion object {
        val callNews: CallAPI = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CallAPI::class.java)
    }

    fun getNewsHeadline(liveData: MutableLiveData<List<Article>>) {
        val retrofit = callNews.getNewsHeadline("us", API_KEY)
        retrofit.enqueue(object : Callback<NewsHeadlines> {
            override fun onResponse(call: Call<NewsHeadlines>, response: Response<NewsHeadlines>) {
                val responseBody = response.body()!!
                liveData.value = responseBody.articles
            }

            override fun onFailure(call: Call<NewsHeadlines>, t: Throwable) {
            }
        })
    }

    fun getNewsSearch(liveData: MutableLiveData<List<Article>>, q: String) {
        val retrofit = callNews.searchNews(q, "publishedAt", API_KEY)
        retrofit.enqueue(object : Callback<NewsHeadlines> {
            override fun onResponse(call: Call<NewsHeadlines>, response: Response<NewsHeadlines>) {
                val responseBody = response.body()!!
                liveData.value = responseBody.articles
            }

            override fun onFailure(call: Call<NewsHeadlines>, t: Throwable) {
            }
        })
    }
}