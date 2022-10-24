package com.example.androiddevelopment.data.repository

import com.example.androiddevelopment.data.remote.NewsRemoteDataSource
import com.example.androiddevelopment.ui.model.news.NewsModel
import io.reactivex.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource
) {
    fun verifyApi(key: String): Single<NewsModel> =
        newsRemoteDataSource.verifyApi(key)
}