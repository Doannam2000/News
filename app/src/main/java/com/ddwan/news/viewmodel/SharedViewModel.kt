package com.ddwan.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddwan.news.model.Article
import com.ddwan.news.repository.NewsRepository

class SharedViewModel:ViewModel() {
    var listNewsHeadlines = MutableLiveData<List<Article>>()
    private val repository = NewsRepository()

    fun getListNewHeadlines(){
        repository.getNewsHeadline(listNewsHeadlines)
    }
    fun getNewsSearch(q:String){
        repository.getNewsSearch(listNewsHeadlines,q)
    }
}