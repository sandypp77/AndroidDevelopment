package com.example.androiddevelopment.ui.activity

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.androiddevelopment.databinding.ActivityDetailedNewsBinding
import com.example.androiddevelopment.ui.adapter.NewsAdapter
import com.example.androiddevelopment.ui.model.news.Article
import com.example.androiddevelopment.ui.viewmodel.NewsViewModel
import com.example.androiddevelopment.utils.ResponseStatus
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailedNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailedNewsBinding
    private val viewModel: NewsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedNewsBinding.inflate(layoutInflater)
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

            binding.btnFavorites.setOnClickListener {
                viewModel.insertNewsLiveData(article)
            }
            setObserver()
        }
    }

    private fun setObserver(){
        viewModel.getInsertNewsLiveData().observe(this) {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    Toast.makeText(
                        this@DetailedNewsActivity, "Success insert data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                ResponseStatus.ERROR -> {
                    Toast.makeText(
                        this@DetailedNewsActivity, it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {}
            }
        }
    }
}