package com.ddwan.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddwan.news.model.Article
import com.ddwan.news.repository.NewsRepository

class SharedViewModel : ViewModel() {

    var listNewsHeadlines = MutableLiveData<List<Article>>()
    private val repository = NewsRepository()
    var check = 0

    fun getListNewHeadlines() {
        repository.getNewsHeadline(listNewsHeadlines)
    }

    fun getNewsSearch(q: String) {
        repository.getNewsSearch(listNewsHeadlines, q)
    }

    fun checkStartActivity(): Boolean {
        return when (check) {
            2 -> true
            1 -> {
                check = 0
                false
            }
            else -> false
        }
    }

    fun changeData() {
        if (check == 0)
            check = 2
        else if (check == 2)
            check = 0
    }
}