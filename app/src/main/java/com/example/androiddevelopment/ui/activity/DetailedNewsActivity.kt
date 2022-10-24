package com.example.androiddevelopment.ui.activity

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.androiddevelopment.databinding.ActivityDetailedNewsBinding
import com.example.androiddevelopment.ui.model.news.Article
import java.time.Duration
import java.util.*
import kotlin.concurrent.schedule

class DetailedNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailedNewsBinding
    private var progressStatus = 0
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

//    private fun startProgress() {
//        Thread {
//            while (progressStatus < 100) {
//                progressStatus += 1
//                binding.loader.progress = progressStatus
//                try {
//                    Thread.sleep(10)
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }
//            }
//        }.start()
//    }
}