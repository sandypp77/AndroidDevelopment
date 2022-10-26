package com.example.androiddevelopment.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androiddevelopment.databinding.ItemNewsBinding
import com.example.androiddevelopment.ui.fragment.FavoriteFragment
import com.example.androiddevelopment.ui.model.news.Article

class NewsAdapter(
    private var newsModel: List<Article>,
    private val newsSelectedCallBack: NewsSelectedCallBack
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var onItemClick: ((Article) -> Unit)? = null

    inner class ViewHolder(var binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                newsSelectedCallBack.onNewsSelected(newsModel[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsModel = newsModel[position]
        with(holder) {
            Glide.with(binding.image.context).load(newsModel.urlToImage).into(binding.image)
            binding.tvTitle.text = newsModel.title
            binding.tvAuthor.text = newsModel.author
//            itemView.setOnClickListener {
//                onItemClick?.invoke(newsModel)
//            }
        }
    }

    override fun getItemCount(): Int {
        return newsModel.size
    }

    interface NewsSelectedCallBack {
        fun onNewsSelected(article: Article)
    }
}