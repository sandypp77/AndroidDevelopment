package com.example.androiddevelopment.data.local

import com.example.androiddevelopment.room.NewsDao
import com.example.androiddevelopment.room.model.NewsEntity
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(private val newsDao: NewsDao) {
    fun getFavoriteNews(): Flowable<List<NewsEntity>> {
        return newsDao.getFavoriteNews()
    }

    fun insertNews(newsEntity: NewsEntity): Single<Long> {
        return newsDao.insertNews(newsEntity)
    }
}