package com.ddwan.news.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ddwan.news.config.Constants.Companion.IS_FIRST
import com.ddwan.news.config.Constants.Companion.KEY
import com.ddwan.news.config.Constants.Companion.KEY_SHARED_PREFERENCES
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PassViewModel(application: Application) : AndroidViewModel(application) {

    val check = MutableLiveData<Int>()
    val imageDrawable = MutableLiveData<Int>()
    var password = ""
    var pass = ""
    var isStartActivity = false
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences(KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    init {
        password = sharedPreferences.getString(KEY, "")!!
        if (password.isEmpty()) check.value = 4
    }

    fun checkPass(i: Int) {
        if (i == 10 && pass.isNotEmpty())
            pass = pass.substring(0, pass.length - 1)
        else if (i != 10 && pass.length < 4)
            pass += i
        changeText()
    }

    private fun changeText() {
        imageDrawable.value = pass.length
        if (pass.length == 4) {
            if (password.isEmpty()) {
                password = pass
                sharedPreferences.edit().putString(KEY, pass).apply()
                sharedPreferences.edit().putBoolean(IS_FIRST, false).apply()
                check.value = 3
                GlobalScope.launch {
                    delay(1000)
                    check.postValue(1)
                }
            } else {
                if (pass == password) {
                    check.value = if (isStartActivity) 5 else 1
                } else {
                    check.value = 2
                    GlobalScope.launch {
                        delay(1000)
                        pass = ""
                        imageDrawable.postValue(0)
                        check.postValue(0)
                    }
                }
            }
        }
    }
}