package com.ddwan.news.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddwan.news.R
import com.ddwan.news.adapter.RecyclerViewAdapter
import com.ddwan.news.config.Constants
import com.ddwan.news.model.Article
import com.ddwan.news.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var adapter: RecyclerViewAdapter? = null
    private val model by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // setup ViewModel
        setUpModel()

        // setup searchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                model.getNewsSearch(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty())
                    model.getListNewHeadlines()
                return false
            }
        })
    }

    private fun setUpModel() {
        model.listNewsHeadlines.observe(this, {
            setUpRecyclerView(it as ArrayList<Article>)
        })
        model.getListNewHeadlines()
    }

    private fun setUpRecyclerView(list: ArrayList<Article>) {
        adapter = RecyclerViewAdapter(list)
        adapter?.setCallback {
            model.check = 1
            val intent = Intent(this, WebView::class.java)
            intent.putExtra(Constants.URL, list[it].url)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}