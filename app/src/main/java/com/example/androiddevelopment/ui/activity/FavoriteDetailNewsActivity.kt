package com.example.androiddevelopment.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.androiddevelopment.databinding.ActivityFavoriteDetailNewsBinding
import com.example.androiddevelopment.ui.model.news.Article
import com.example.androiddevelopment.ui.viewmodel.NewsViewModel

class FavoriteDetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteDetailNewsBinding
    private val viewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val article = intent.getParcelableExtra<Article>("article")
        if(article != null){
            Glide.with(binding.imageView.context).load(article.urlToImage).into(binding.imageView)
            binding.tvTitle.text = article.title
            binding.tvDesc.text = article.description
            binding.tvDate.text = article.publishedAt

            //webview
            binding.webView.settings.domStorageEnabled = true
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.settings.loadsImagesAutomatically = true
            binding.webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            binding.webView.webViewClient = WebViewClient()
            binding.webView.loadUrl(article.url)

            if(binding.webView.isShown){
                binding.webViewLoader.visibility = View.INVISIBLE
            }
        }
    }
}