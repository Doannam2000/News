package com.ddwan.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ddwan.news.R
import com.ddwan.news.config.Constants.Companion.inputFormatter
import com.ddwan.news.config.Constants.Companion.outputFormatter
import com.ddwan.news.model.Article

class RecyclerViewAdapter(var list: ArrayList<Article>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var context: Context
    lateinit var itemClick: (position: Int) -> Unit
    fun setCallback(click: (position: Int) -> Unit) {
        itemClick = click
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerViewAdapter.ViewHolder {
        context = parent.context
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.setData()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imageView)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val sourceName: TextView = itemView.findViewById(R.id.source_name)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val description: TextView = itemView.findViewById(R.id.description)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.container)
        fun setData() {
            val article = list[adapterPosition]
            Glide.with(context)
                .load(article.urlToImage)
                .placeholder(R.drawable.news_image)
                .into(image)
            title.text = article.title
            sourceName.text = article.source.name
            date.text = outputFormatter.format(inputFormatter.parse(article.publishedAt))
            description.text = article.description
            layout.setOnClickListener { itemClick.invoke(adapterPosition) }
        }
    }
}