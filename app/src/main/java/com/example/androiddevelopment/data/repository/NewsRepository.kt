package com.example.androiddevelopment.data.repository

import com.example.androiddevelopment.data.local.NewsLocalDataSource
import com.example.androiddevelopment.data.remote.NewsRemoteDataSource
import com.example.androiddevelopment.room.model.NewsEntity
import com.example.androiddevelopment.ui.model.news.Article
import com.example.androiddevelopment.ui.model.news.NewsModel
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) {
    fun verifyApi(key: String): Single<NewsModel> =
        newsRemoteDataSource.verifyApi(key)

    fun insertNews(article: Article): Single<Long> {
        val newsEntity = NewsEntity(
            name = article.title,
            imageUrl = article.urlToImage,
            description = article.description,
            url = article.url,
            publishedAt = article.publishedAt
        )
        return newsLocalDataSource.insertNews(newsEntity)
    }

    fun getFavoriteNews(): Flowable<List<Article>> {
        return newsLocalDataSource.getFavoriteNews().map{
            toNewsResponses(it)
        }
    }

    private fun toNewsResponse(newsEntity: NewsEntity): Article {
        return Article(
            "",
            "",
            newsEntity.description,
            newsEntity.publishedAt,
            newsEntity.name,
            newsEntity.url,
            newsEntity.imageUrl
        )
    }

    private fun toNewsResponses(newsEntities: List<NewsEntity>): List<Article>{
        return newsEntities.map{
            toNewsResponse(it)
        }.toList()
    }
}