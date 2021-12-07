package com.ddwan.news

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.ddwan.news.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_web_view.*

class WebView : AppCompatActivity() {

    private val model by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        supportActionBar?.hide()
        val url = intent.extras!!.getString("url") as String
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.databaseEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
    }

    override fun onResume() {
        if (model.checkStartActivity()) {
            val intent = Intent(this, PasswordActivity::class.java)
            intent.putExtra("isStartActivity", true)
            startActivity(intent)
        }
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        model.changeData()
    }
}