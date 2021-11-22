package com.ddwan.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddwan.news.adapter.RecyclerViewAdapter
import com.ddwan.news.model.Article
import com.ddwan.news.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var listNewsHeadlines = ArrayList<Article>()
    private var adapter: RecyclerViewAdapter? = null

    private val model by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        setUpModel()

        // setup searchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                model.getNewsSearch(query!!)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == "" || newText == null)
                    model.getListNewHeadlines()
                return false
            }
        })
    }

    private fun setUpModel(){
        model.listNewsHeadlines.observe(this, {
            setUpRecyclerView(it as ArrayList<Article>)
        })
        model.getListNewHeadlines()
    }

    private fun setUpRecyclerView(it:ArrayList<Article>){
        listNewsHeadlines = it
        adapter = RecyclerViewAdapter(listNewsHeadlines)
        adapter?.setCallback { it ->
            val intent = Intent(this, WebView::class.java)
            intent.putExtra("url", listNewsHeadlines[it].url)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}