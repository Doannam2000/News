package com.ddwan.news.config

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class Constants {
    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = "6656ce48996448df80ad2c7067138a85"

        @SuppressLint("SimpleDateFormat")
        val outputFormatter  = SimpleDateFormat("EEE MMM dd hh:mm aaa", Locale.ENGLISH)
        @SuppressLint("SimpleDateFormat")
        // "2021-11-20T08:00:22Z"
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    }
}