package com.ddwan.news.model

data class NewsHeadlines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,
)