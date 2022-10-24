package com.example.androiddevelopment.data.service

import com.example.androiddevelopment.ui.model.news.NewsModel
import io.reactivex.Single
import retrofit2.http.*

interface NewsService {
    @GET("/v2/everything?domains=wsj.com")
    fun verifyApi(
        @Query("apiKey") key: String
    ): Single<NewsModel>
}