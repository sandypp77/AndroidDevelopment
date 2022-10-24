package com.example.androiddevelopment.data.remote

import com.example.androiddevelopment.data.service.NewsService
import com.example.androiddevelopment.ui.model.news.NewsModel
import io.reactivex.Single
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val newsService: NewsService
) {
    fun verifyApi(key: String): Single<NewsModel> = newsService.verifyApi(key)
}