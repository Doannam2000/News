package com.ddwan.news.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PassViewModel(application: Application) : AndroidViewModel(application) {

    var check = MutableLiveData<Int>()
    var textView = MutableLiveData<String>()
    var password = ""
    var pass = ""
    var isFirst = true
    var isStartActivity = false
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("SHARE_PREFERENCES", Context.MODE_PRIVATE)

    init {
        password = sharedPreferences.getString("key", "")!!
        isFirst = sharedPreferences.getBoolean("isFirst", true)
        if (isFirst) check.value = 4
    }

    fun checkPass(i: Int) {
        when (i) {
            0 -> if (pass.length < 4) pass += 0
            1 -> if (pass.length < 4) pass += 1
            2 -> if (pass.length < 4) pass += 2
            3 -> if (pass.length < 4) pass += 3
            4 -> if (pass.length < 4) pass += 4
            5 -> if (pass.length < 4) pass += 5
            6 -> if (pass.length < 4) pass += 6
            7 -> if (pass.length < 4) pass += 7
            8 -> if (pass.length < 4) pass += 8
            9 -> if (pass.length < 4) pass += 9
            10 -> if (pass.isNotEmpty()) pass = pass.substring(0, pass.length - 1)
        }
        changeText()
    }

    private fun changeText() {
        when (pass.length) {
            0 -> textView.value = ""
            1 -> textView.value = "*"
            2 -> textView.value = "* *"
            3 -> textView.value = "* * *"
            4 -> {
                textView.value = "* * * *"
                if (isFirst) {
                    password = pass
                    sharedPreferences.edit().putString("key", pass).apply()
                    sharedPreferences.edit().putBoolean("isFirst", false).apply()
                    isFirst = false
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
                            textView.postValue("")
                            check.postValue(0)
                        }
                    }
                }
            }
        }
    }
}